package installer.commands.wrappers;

import installer.commands.Operation;

public class BackgroundOperation implements Operation {
    Operation op;
    boolean isFinished;

    public BackgroundOperation(Operation op) {
        this.op = op;
        this.isFinished = false;
    }

    @Override
    public void revert() throws Exception {
        op.revert();
    }

    @Override
    public void apply() throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    op.apply();
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
