package installer.commands.read;

import installer.commands.Command;

public class GetPermissionsOfFile implements Command {
    String file;

    public GetPermissionsOfFile(String file) {
        this.file = file;
    }

    public String getCommand() {
        return "stat -c \"%a\" " + file;
    }
}
