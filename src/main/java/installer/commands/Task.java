package installer.commands;

import java.io.IOException;

public interface Task {

    public void apply() throws Exception;
}
