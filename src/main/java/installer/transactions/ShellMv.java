package installer.transactions;

import com.sun.istack.internal.NotNull;
import installer.commands.read.GetGroupOfFile;
import installer.commands.read.GetOwnerOfFile;
import installer.commands.write.ChownFile;
import installer.commands.write.MvFile;
import installer.operations.ShellOperation;
import installer.operations.SudoShellOperation;

public class ShellMv implements Transaction {

    private final String sudo;
    private final String src;
    private final String dst;

    public ShellMv(@NotNull String src,
                   @NotNull String dst,
                   @NotNull String sudo) {
        this.src = src;
        this.dst = dst;
        this.sudo = sudo; // Sudo required here in order to revert properly.
    }

    public void apply() throws Exception {
        ShellOperation shellOperation =
                new SudoShellOperation(sudo, new MvFile(src, dst));
        shellOperation.execute();
    }

    public void revert() throws Exception {
        ShellOperation shellOperation =
                new SudoShellOperation(sudo, new MvFile(dst, src));
        shellOperation.execute();
    }

    @Override
    public String toString() {
        return "ShellMv{" +
                "sudo='" + sudo + '\'' +
                ", src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }
}
