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

public class CopyTest {
    @Before
    // initialize JShell system
    public void initialize() {
        JShellFileSystem.initJShellFileSystem(new Directory("/", null));
    }

    @After
    public void restore() {
        // as JShell system only initialize once, so restore back the current
        // directory to root, clean the content in directory in order to run
        // the whole test suite at once
        JShellFileSystem
                .setCurrentDir((Directory) JShellFileSystem.getRootDir());
        ((Directory) JShellFileSystem.getRootDir())
                .setDirectoryContents(new ArrayList<FileObject>());
    }

    @Test
    public void testExecute() {
        // get root directory
        Directory root = (Directory) JShellFileSystem.getRootDir();
        // create a new file
        root.add(new File("file", root));
        // setup new directory and add root directory as the parent directory
        Directory test = new Directory("test", root);
        root.add(test);
        // input arguments
        List<String> args =
                new ArrayList<String>(Arrays.asList("cp", "file", "test"));
        // initialize cp command
        Copy cp = new Copy();
        // execute cp command
        cp.execute(args);
        // get the file in destination directory
        File file = (File) test.getFileObjInDirectory("file");
        // check whether the file exists in destination directory
        assertTrue(file != null);
        // get the file in old directory
        file = (File) root.getFileObjInDirectory("file");
        // check whether the file exists in old directory
        assertTrue(file != null);
    }

    @Test
    public void testValidate() {
        // test invalid arguments error
        List<String> args = new ArrayList<String>(Arrays.asList("cp"));
        Copy cp = new Copy();
        assertEquals("JShell: cp: Too many, or too few arguments: cp ",
                cp.execute(args).get(0).toString());
    }
}
