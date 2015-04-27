package installer.commands.read;

import installer.commands.Command;

public class GetGroupOfFile implements Command {
    String file;

    public GetGroupOfFile(String file) {
        this.file = file;
    }

    @Override
    public String getCommand() {
        return "stat -c '%G' " + file;
    }
}
