package installer.commands.wrappers;

import installer.commands.basic.CheckFileExists;
import org.junit.Test;

import java.io.File;

public class TestBackgroundOperation {

    @Test(timeout = 1000L)
    public void testCheckFileExistsBackgroundOp() throws Exception {
        BackgroundOperation backgroundOperation =
                new BackgroundOperation(new CheckFileExists(
                        new File("C:\\Users\\Plamen\\Downloads\\epm.exe")));
        backgroundOperation.apply();
        while(!backgroundOperation.isFinished) {
            // Do nothing.
        }
    }
}
