package installer.exceptions;

public class FatalInstallerException extends Exception {

  private Exception applyEx;
  private Exception revertEx;
  
  public FatalInstallerException() {}

  public FatalInstallerException(Exception applyEx, Exception revertEx) {
    this.applyEx = applyEx;
    this.revertEx = revertEx;
  }

  public void setApplyEx(Exception applyEx) {
    this.applyEx = applyEx;
  }
  
  public void setRevertEx(Exception revertEx) {
    this.revertEx = revertEx;
  }

  @Override
  public String toString() {
    return "Apply exception: " + applyEx.toString() + 
        "\nRevert exception:" + revertEx.toString();
  }
}
