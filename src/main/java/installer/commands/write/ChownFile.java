package installer.commands.write;

import installer.commands.Command;

public class ChownFile implements Command {
    String file;
    String user;
    String group;

    public ChownFile(String file, String user, String group) {
        this.file = file;
        this.user = user;
        this.group = group;
    }

    public String getCommand() {
        return "chown " + user + ":" + group + " " + file;
    }
}