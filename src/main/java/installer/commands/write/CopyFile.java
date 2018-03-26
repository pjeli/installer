package installer.commands.write;

import installer.commands.Command;

public class CopyFile implements Command {
    String src;
    String dst;

    public CopyFile(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getCommand() {
        return "cp " + src + " " + dst;
    }
}
