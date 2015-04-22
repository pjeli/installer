package installer.transactions;

import installer.commands.read.GetPermissionsOfFile;
import installer.commands.write.ChmodFile;
import installer.operations.ShellOperation;

public class ShellChmod implements Transaction {
    String file;
    String mode;
    String originalMode;

    public ShellChmod(String file, String mode) {
        this.file = file;
        this.mode = mode;
    }

    public void apply() throws Exception {
        ShellOperation op = new ShellOperation(new GetPermissionsOfFile(file));
        op.execute();
        originalMode = op.getOutput()[0];
        ShellOperation op2 = new ShellOperation(new ChmodFile(mode, file));
        op2.execute();
    }

    public void revert() throws Exception {
        ShellOperation op = new ShellOperation(new ChmodFile(originalMode, file));
        op.execute();
    }
}
