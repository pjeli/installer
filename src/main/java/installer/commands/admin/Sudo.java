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
    if(user != null) {
      return "sudo -u " + user + " " + command.getCommand();
    } else {
      return command.getCommand();
    }
  }
}
