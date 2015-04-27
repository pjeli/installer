package installer.operations.wrappers;

import installer.commands.read.GetGroupOfFile;
import installer.commands.read.GetOwnerOfFile;
import installer.commands.read.GetPermissionsOfFile;
import installer.operations.ShellOperation;
import org.junit.Test;

public class TestGetPermissionsOfFile {

  @Test
  public void testGetPermissionsOfFile() throws Exception {
      ShellOperation shellOperation = new ShellOperation(
          new GetPermissionsOfFile("/etc/hosts"));
      shellOperation.execute();
      assert shellOperation.getError()[0].equals("644");
  }

  @Test
  public void testGetUserOfFile() throws Exception {
    ShellOperation shellOperation = new ShellOperation(
        new GetOwnerOfFile("/etc/hosts"));
    shellOperation.execute();
    assert shellOperation.getError()[0].equals("root");
  }

  @Test
  public void testGetGroupOfFile() throws Exception {
    ShellOperation shellOperation = new ShellOperation(
        new GetGroupOfFile("/etc/hosts"));
    shellOperation.execute();
    assert shellOperation.getError()[0].equals("root");
  }
}
