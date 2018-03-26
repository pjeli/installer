package installer.commands.admin;

import installer.commands.Command;

public class Sudo implements Command {
  String user;
  Command command;
  
  public Sudo(String user, Command command) {
    this.user = user;
    this.command = command;
  }
  
  public String getCommand() {
    return "sudo -u " + user + " " + command.getCommand();
  }
}
