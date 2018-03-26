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
    String[] errors;

    public ShellOperation(Command command) {
        this.command = command;
    }

    public void execute() throws Exception {
        Process start = Runtime.getRuntime().exec(command.getCommand());
        this.exitValue = start.waitFor();
        BufferedReader read = new BufferedReader(new InputStreamReader(
            start.getInputStream()));
        BufferedReader read2 = new BufferedReader(new InputStreamReader(
            start.getErrorStream()));
        List<String> outputs = new ArrayList<String>();
        List<String> errors = new ArrayList<String>();
        String output;
        while((output = read.readLine()) != null) {
            outputs.add(output);
        }
        this.outputs = outputs.toArray(new String[outputs.size()]);
        while((output = read2.readLine()) != null) {
          errors.add(output);
        }
        this.errors = errors.toArray(new String[errors.size()]);
    }

    public int getExitValue() {
        return exitValue;
    }

    public String[] getOutput() {
        return outputs;
    }

    public String[] getError() {
        return errors;
    }
}
