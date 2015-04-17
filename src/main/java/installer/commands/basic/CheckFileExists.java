package installer.commands.basic;

import installer.commands.Operation;

import java.io.File;
import java.io.FileNotFoundException;

public class CheckFileExists implements Operation {
    File file;

    public CheckFileExists(File file) {
        this.file = file;
    }

    @java.lang.Override
    public void revert() {
        // Do nothing.
    }

    @java.lang.Override
    public void apply() throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException(file.getAbsolutePath());
    }
}
