package installer.commands.basic;

import com.google.common.io.Files;
import installer.commands.Operation;

import java.io.File;

public class CopyFile implements Operation {
    File file;
    String putPath;

    public CopyFile(File file, String putPath) {
        this.file = file;
        this.putPath = putPath;
    }

    public void revert() throws Exception {
        File file = new File(putPath);
        if(file.exists()) {
            boolean deleted = file.delete();
            if(!deleted)
                throw new Exception("Couldn't delete: " + file.toString());
        }
    }

    public void apply() throws Exception {
        Files.copy(file, new File(putPath));
    }
}
