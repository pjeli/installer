package installer.operations;

import installer.commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellOperation implements Operation {
    Command command;
    int exitValue;
    String[] outputs;

    public ShellOperation(Command command) {
        this.command = command;
    }

    @Override
    public void execute() throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command.getCommand());
        Process start = pb.start();
        this.exitValue = start.exitValue();
        BufferedReader read = new BufferedReader(new InputStreamReader(
            start.getInputStream()));
        List<String> outputs = new ArrayList<String>();
        while(read.ready()) {
            String output = read.readLine();
            outputs.add(output);
        }
        this.outputs = outputs.toArray(new String[outputs.size()]);
    }

    public int getExitValue() {
        return exitValue;
    }

    public String[] getOutput() {
        return outputs;
    }
}
