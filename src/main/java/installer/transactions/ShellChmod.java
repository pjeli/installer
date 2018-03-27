package installer.transactions;

import com.sun.istack.internal.NotNull;
import installer.commands.read.GetPermissionsOfFile;
import installer.commands.write.ChmodFile;
import installer.operations.ShellOperation;
import installer.operations.SudoShellOperation;

public class ShellChmod implements Transaction {

    private final String sudo;
    private final String file;
    private final String mode;

    private String originalMode;

    public ShellChmod(@NotNull String file, 
                      @NotNull String mode, 
                      @NotNull String sudo) {
        this.file = file;
        this.mode = mode;
        this.sudo = sudo;
    }

    public void apply() throws Exception {
        ShellOperation op = new SudoShellOperation(sudo, new GetPermissionsOfFile(file));
        op.execute();
        originalMode = op.getOutput()[0];
        ShellOperation op2 = new SudoShellOperation(sudo, new ChmodFile(mode, file));
        op2.execute();
    }

    public void revert() throws Exception {
        ShellOperation op = new SudoShellOperation(sudo, new ChmodFile(originalMode, file));
        op.execute();
    }
}
