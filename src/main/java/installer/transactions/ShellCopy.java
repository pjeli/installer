package installer.transactions;

import com.sun.istack.internal.NotNull;
import installer.commands.write.CopyFile;
import installer.commands.write.RmFile;
import installer.operations.ShellOperation;
import installer.operations.SudoShellOperation;

public class ShellCopy implements Transaction {

    private final String sudo;
    private final String src;
    private final String dst;

    public ShellCopy(@NotNull String src,
                     @NotNull String dst,
                     @NotNull String sudo) {
        this.src = src;
        this.dst = dst;
        this.sudo = sudo; // Sudo required here in order to revert properly.
    }

    public void apply() throws Exception {
        ShellOperation shellOperation =
                new SudoShellOperation(sudo, new CopyFile(src, dst));
        shellOperation.execute();
    }

    public void revert() throws Exception {
        ShellOperation shellOperation =
                new SudoShellOperation(sudo, new RmFile(dst));
        shellOperation.execute();
    }
}
