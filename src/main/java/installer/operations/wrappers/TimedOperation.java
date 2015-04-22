package installer.operations.wrappers;

import installer.operations.Operation;

import java.util.concurrent.TimeoutException;

public class TimedOperation implements Operation {
    Operation op;
    long milliSecsAllowed;

    TimedOperation(Operation op, long milliSecsAllowed) {
        this.op = op;
        this.milliSecsAllowed = milliSecsAllowed;
    }

    public void execute() throws Exception {
        long currentTime = System.currentTimeMillis();
        BackgroundOperation backgroundOperation = new BackgroundOperation(op);
        backgroundOperation.execute();
        while(!backgroundOperation.isFinished) {
            if(System.currentTimeMillis() > currentTime + milliSecsAllowed) {
                throw new TimeoutException();
            }
        }
    }
}
