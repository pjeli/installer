package installer.transactions;

import com.sun.istack.internal.NotNull;
import installer.commands.write.MvFile;
import installer.commands.write.RmFile;
import installer.operations.ShellOperation;
import installer.operations.SudoShellOperation;

public class ShellRm implements Transaction {

    private final String sudo;
    private final String file;

    public ShellRm(@NotNull String file,
                   @NotNull String sudo) {
        this.file = file;
        this.sudo = sudo; // Sudo required here in order to revert properly.
    }

    public void apply() throws Exception {
        ShellOperation shellOperation =
                new SudoShellOperation(sudo, new RmFile(file));
        shellOperation.execute();
    }

    public void revert() throws Exception {
        throw new Exception("Cannot revert");
    }
}
