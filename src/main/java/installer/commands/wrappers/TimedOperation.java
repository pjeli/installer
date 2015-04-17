package installer.commands.wrappers;

import installer.commands.Operation;

import java.util.concurrent.TimeoutException;

public class TimedOperation implements Operation {
    Operation op;
    long milliSecsAllowed;

    TimedOperation(Operation op, long milliSecsAllowed) {
        this.op = op;
        this.milliSecsAllowed = milliSecsAllowed;
    }

    @Override
    public void revert() throws Exception {
        op.revert();
    }

    @Override
    public void apply() throws Exception {
        long currentTime = System.currentTimeMillis();
        BackgroundOperation backgroundOperation = new BackgroundOperation(op);
        backgroundOperation.apply();
        while(!backgroundOperation.isFinished) {
            if(System.currentTimeMillis() > currentTime + milliSecsAllowed) {
                throw new TimeoutException();
            }
        }
    }
}
