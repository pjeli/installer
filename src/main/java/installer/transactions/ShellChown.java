package installer.transactions;

import installer.commands.read.GetGroupOfFile;
import installer.commands.read.GetOwnerOfFile;
import installer.commands.write.ChownFile;
import installer.operations.ShellOperation;

public class ShellChown implements Transaction {
    String file;
    String user;
    String group;

    String originalUser;
    String originalGroup;

    public ShellChown(String file, String user, String group) {
        this.file = file;
        this.user = user;
        this.group = group;
    }

    @Override
    public void apply() throws Exception {
        ShellOperation getOwnerOp = new ShellOperation(
            new GetOwnerOfFile(file));
        getOwnerOp.execute();
        originalUser = getOwnerOp.getError()[0];
        ShellOperation getGroupOp =
            new ShellOperation(new GetGroupOfFile(file));
        getGroupOp.execute();
        originalGroup = getGroupOp.getError()[0];
        ShellOperation shellOperation =
            new ShellOperation(new ChownFile(file, user, group));
        shellOperation.execute();
    }

    @Override
    public void revert() throws Exception {
        ShellOperation shellOperation = new ShellOperation(
            new ChownFile(file, originalUser, originalGroup));
        shellOperation.execute();
    }
}
