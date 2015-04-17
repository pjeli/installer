package installer.commands.basic;

import installer.commands.Operation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;

public class ChownFile implements Operation {
    File file;
    String user;
    String group;

    String originalUser;
    String originalGroup;

    public ChownFile(File file, String user, String group) {
        this.file = file;
        this.user = user;
        this.group = group;
    }

    public void revert() throws Exception {
        PosixFileAttributeView fileAttributeView =
                Files.getFileAttributeView(Paths.get(file.toURI()),
                        PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        fileAttributeView.setGroup(new GroupPrincipal() {
            @Override
            public String getName() {
                return originalGroup;
            }
        });
        fileAttributeView.setOwner(new UserPrincipal() {
            @Override
            public String getName() {
                return originalUser;
            }
        });
    }

    public void apply() throws Exception {
        PosixFileAttributeView fileAttributeView =
                Files.getFileAttributeView(Paths.get(file.toURI()),
                    PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        PosixFileAttributes posixFileAttributes =
                fileAttributeView.readAttributes();
        originalGroup = posixFileAttributes.group().getName();
        originalUser = posixFileAttributes.owner().getName();
        if(group != null) {
            fileAttributeView.setGroup(new GroupPrincipal() {
                @Override
                public String getName() {
                    return group;
                }
            });
        }
        if(user != null) {
            fileAttributeView.setOwner(new UserPrincipal() {
                @Override
                public String getName() {
                    return user;
                }
            });
        }
    }
}
