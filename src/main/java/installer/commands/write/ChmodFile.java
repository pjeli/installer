package installer.commands.write;

import installer.commands.Command;

public class ChmodFile implements Command {
    String mode;
    String file;

    public ChmodFile(String mode, String file) {
        this.mode = mode;
        this.file = file;
    }

    public String getCommand() {
        return "chmod " + mode + " " + file;
    }
}
