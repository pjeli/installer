package installer.operations;


import installer.commands.Command;
import installer.commands.admin.Sudo;

public class SudoShellOperation extends ShellOperation {
  
  public SudoShellOperation(String user, Command command) {
    super(new Sudo(user, command));
  }
}
