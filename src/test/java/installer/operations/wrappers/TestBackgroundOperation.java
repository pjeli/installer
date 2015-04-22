package installer.operations.wrappers;

import installer.commands.read.CheckFileExists;
import installer.operations.ShellOperation;
import org.junit.Test;

public class TestBackgroundOperation {

    @Test(timeout = 1000L)
    public void testCheckFileExistsBackgroundOp() throws Exception {
        BackgroundOperation backgroundOperation =
                new BackgroundOperation(new ShellOperation(new CheckFileExists(
                        "C:\\Users\\Plamen\\Downloads\\epm.exe")));
        backgroundOperation.execute();
        while(!backgroundOperation.isFinished) {
            // Do nothing.
        }
    }
}
