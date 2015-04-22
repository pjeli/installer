package installer.operations.wrappers;

import installer.operations.Operation;

public class BackgroundOperation implements Operation {
    Operation op;
    boolean isFinished;

    public BackgroundOperation(Operation op) {
        this.op = op;
        this.isFinished = false;
    }

    public void execute() throws Exception {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    op.execute();
                    isFinished = true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
