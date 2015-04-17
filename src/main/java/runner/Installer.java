package runner;

import installer.commands.Task;

public interface Installer {
    public void addTask(Task task);

    public void run();

    public void undo();
}
