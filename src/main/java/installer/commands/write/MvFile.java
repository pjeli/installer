package installer.commands.write;

import installer.commands.Command;

public class MvFile implements Command {
    String src;
    String dst;

    public MvFile(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getCommand() {
        return "mv " + src + " " + dst;
    }
}
