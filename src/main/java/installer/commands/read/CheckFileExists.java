package installer.commands.read;

import installer.commands.Command;

public class CheckFileExists implements Command {
    String file;

    public CheckFileExists(String file) {
        this.file = file;
    }

    public String getCommand() {
        return "[ -f " + file + " ]";
    }
}
