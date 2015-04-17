package runner.example;

import installer.commands.basic.ChmodFile;
import installer.commands.basic.ChownFile;
import installer.commands.wrappers.BackgroundOperation;

public class ExampleMain {

    public static void main(String[] args) {
        ExampleInstaller exampleInstaller = new ExampleInstaller();
        initializeInstaller(exampleInstaller);
        exampleInstaller.run();
    }

    private static void initializeInstaller(ExampleInstaller exampleInstaller) {
        exampleInstaller.addTask(new ChmodFile());
        exampleInstaller.addTask(new BackgroundOperation(
                new ChownFile(null, null, null)));
    }
}
