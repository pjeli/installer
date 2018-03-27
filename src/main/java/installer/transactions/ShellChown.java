package installer.transactions;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import installer.commands.read.GetGroupOfFile;
import installer.commands.read.GetOwnerOfFile;
import installer.commands.write.ChownFile;
import installer.operations.ShellOperation;
import installer.operations.SudoShellOperation;

public class ShellChown implements Transaction {
    
    private final String sudo;
    private final String file;
    private final String user;
    private final String group;

    private String originalUser;
    private String originalGroup;

    public ShellChown(@NotNull String file, 
                      @NotNull String user, 
                      @NotNull String group,
                      @NotNull String sudo) {
        this.file = file;
        this.user = user;
        this.group = group;
        this.sudo = sudo; // Sudo required here in order to revert properly.
    }

    public void apply() throws Exception {
        ShellOperation getOwnerOp = new SudoShellOperation(sudo,
            new GetOwnerOfFile(file));
        getOwnerOp.execute();
        originalUser = getOwnerOp.getError()[0];
        ShellOperation getGroupOp =
            new SudoShellOperation(sudo, new GetGroupOfFile(file));
        getGroupOp.execute();
        originalGroup = getGroupOp.getError()[0];
        ShellOperation shellOperation =
            new SudoShellOperation(sudo, new ChownFile(file, user, group));
        shellOperation.execute();
    }

    public void revert() throws Exception {
        ShellOperation shellOperation = new SudoShellOperation(sudo,
            new ChownFile(file, originalUser, originalGroup));
        shellOperation.execute();
    }

    @Override
    public String toString() {
        return "ShellChown{" +
            "sudo='" + sudo + '\'' +
            ", file='" + file + '\'' +
            ", user='" + user + '\'' +
            ", group='" + group + '\'' +
            ", originalUser='" + originalUser + '\'' +
            ", originalGroup='" + originalGroup + '\'' +
            '}';
    }
}
