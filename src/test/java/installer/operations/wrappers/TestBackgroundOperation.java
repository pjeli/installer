package installer.operations.wrappers;

import installer.commands.read.CheckFileExists;
import installer.operations.ShellOperation;
import org.junit.Test;

public class TestBackgroundOperation {

    @Test(timeout = 1000L)
    public void testCheckFileExistsBackgroundOp() throws Exception {
        ShellOperation shellOperation = new ShellOperation(new CheckFileExists(
            "/etc/hosts"));
        BackgroundOperation backgroundOperation =
            new BackgroundOperation(shellOperation);
        backgroundOperation.execute();
        while(!backgroundOperation.isFinished()) {
            // Do nothing.
        }
        assert shellOperation.getExitValue() == 0;
    }

    @Test(timeout = 1000L)
    public void testCheckFileDoesntExistBackgroundOp() throws Exception {
        ShellOperation shellOperation = new ShellOperation(new CheckFileExists(
            "/etc/hosts2"));
        BackgroundOperation backgroundOperation =
            new BackgroundOperation(shellOperation);
        backgroundOperation.execute();
        while(!backgroundOperation.isFinished()) {
          // Do nothing.
        }
        assert shellOperation.getExitValue() == 1;
    }
}
