package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import JShellfilesystem.JShellFileSystem;
import commands.*;
import filetypes.*;

public class PopDirectoryTest {

    @Before
    // initialize JShell system
    public void initialize() {
        JShellFileSystem.initJShellFileSystem(new Directory("/", null));
    }

    @After
    public void restore() {
        // as JShell system only initialize once, so restore back the current
        // directory to root, empty the directory stack and clean the content
        // in directory in order to run the whole test suite at once
        JShellFileSystem
                .setCurrentDir((Directory) JShellFileSystem.getRootDir());
        while (!JShellFileSystem.getDirectoryStack().isEmpty()) {
            JShellFileSystem.getDirectoryStack().pop();
        }
        ((Directory) JShellFileSystem.getRootDir())
                .setDirectoryContents(new ArrayList<FileObject>());
    }

    @Test
    public void testExecute() {
        // get root directory
        Directory root = (Directory) JShellFileSystem.getRootDir();
        // setup new directories and add root directory as the parent directory
        Directory test1 = new Directory("test1", root);
        root.add(test1);
        Directory test2 = new Directory("test2", root);
        root.add(test2);
        // push the directories to directory stack for testing
        JShellFileSystem.getDirectoryStack().push(test1);
        JShellFileSystem.getDirectoryStack().push(test2);
        // input arguments
        List<String> args1 = new ArrayList<String>(Arrays.asList("popd"));
        List<String> args2 = new ArrayList<String>(Arrays.asList("popd"));
        // initialize popd command
        PopDirectory popd = new PopDirectory();
        // execute popd command
        popd.execute(args1);
        // check whether the working directory has changed to the previous one
        assertEquals(test2, JShellFileSystem.getCurrentDir());
        // execute popd command
        popd.execute(args2);
        // check whether the working directory has changed to the previous one
        assertEquals(test1, JShellFileSystem.getCurrentDir());
    }

    @Test
    public void testDirectoryStackIsEmptyError() {
        List<String> args = new ArrayList<String>(Arrays.asList("popd"));
        PopDirectory popd = new PopDirectory();
        // clean the directory stack to empty
        restore();
        assertEquals("Directory Stack is empty. Please push a directory first",
                popd.execute(args).get(0).toString());
    }
}
