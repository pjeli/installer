package runner.example;

import installer.commands.Revertable;
import installer.commands.Task;
import runner.Installer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExampleInstaller implements Installer {
    List<Task> taskList;
    int numCompleted;

    ExampleInstaller() {
        this.taskList = new ArrayList<Task>(1000);
    }

    @Override
    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void run() {
        for(Task task : taskList) {
            try {
                task.apply();
                numCompleted++;
            } catch (Exception e) {
                System.out.println(e.toString());
                undo();
            }
        }
    }

    @Override
    public void undo() {
        for(int i = numCompleted; i > 0; i--) {
            Task task = taskList.get(i);
            if(task instanceof Revertable) {
                try {
                    ((Revertable) task).revert();
                } catch (Exception e) {
                    System.out.println(e.toString());
                    System.out.println("Continue reverting? (y/n)");
                    try {
                        char read = (char) System.in.read();
                        switch (read) {
                            case 'y':
                                break;
                            case 'n':
                                System.exit(1);
                                break;
                            default:
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
