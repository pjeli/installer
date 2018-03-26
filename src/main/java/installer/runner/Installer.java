package installer.runner;

import installer.operations.Operation;

public interface Installer {
    /**
     * Appends an operation to the installer. This added operation will
     * be performed after all the other added ones.
     * @param operation the operation to perform next
     */
    void addOperation(Operation operation);

    void run();

    void undo();
}
