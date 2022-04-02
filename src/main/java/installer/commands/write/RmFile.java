package installer.commands.write;

import installer.commands.Command;

public class RmFile implements Command {
    String file;

    public RmFile(String file) {
        this.file = file;
    }

    public String getCommand() {
        return "rm " + file;
    }
}
