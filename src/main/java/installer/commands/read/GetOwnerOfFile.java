package installer.commands.read;

import installer.commands.Command;

public class GetOwnerOfFile implements Command {
    String file;

    public GetOwnerOfFile(String file) {
        this.file = file;
    }

    @Override
    public String getCommand() {
        return "stat -c '%U' " + file;
    }
}
