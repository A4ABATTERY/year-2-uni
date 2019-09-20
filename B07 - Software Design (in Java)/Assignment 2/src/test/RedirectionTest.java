package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import Hashmap.HashMapCom;
import JShellReturnTypes.*;
import JShellfilesystem.JShellFileSystem;
import Redirection.Redirection;
import filetypes.*;

public class RedirectionTest {
    @BeforeClass
    // initialize JShell system
    public static void initialize() {
        JShellFileSystem.initJShellFileSystem(new Directory("/", null));
        HashMapCom.initHashMap();
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
    // test overwrite redirection mode
    public void testOverwrite() {
        // input arguments
        List<String> args = new ArrayList<String>(
                Arrays.asList("echo", "new line", ">", "file1"));
        // call redirection handler
        List<RetType> retType = Redirection.redirectionHandler(args);
        Directory dir = (Directory) JShellFileSystem.getCurrentDir();
        // get the saved file
        File file = (File) dir.getFileObjInDirectory("file1");
        // get the file content
        String output = file.toString();
        assertEquals("new line", output);
        // check their is no standard output
        assertTrue(retType.contains(null));
    }

    @Test
    // test append redirection mode
    public void testAppend() {
        Directory dir = (Directory) JShellFileSystem.getCurrentDir();
        // create a new file with content
        dir.add(new File("file2", dir, "line 1"));
        // input arguments for appending
        List<String> args = new ArrayList<String>(
                Arrays.asList("echo", "line 2", ">>", "file2"));
        // call redirection handler
        List<RetType> retType = Redirection.redirectionHandler(args);
        // get the specified file
        File file = (File) dir.getFileObjInDirectory("file2");
        // get the file content
        String output = file.toString();
        assertEquals("line 1\nline 2", output);
        // check their is no standard output
        assertTrue(retType.contains(null));
    }

    @Test
    public void testCommandOtherThanEcho() {
        Directory dir = (Directory) JShellFileSystem.getCurrentDir();
        // create a new file for ls command output
        dir.add(new File("file3", dir));
        // input arguments
        List<String> args =
                new ArrayList<String>(Arrays.asList("ls", ">", "file3"));
        // call redirection handler
        List<RetType> retType = Redirection.redirectionHandler(args);
        // get the specified file
        File file = (File) dir.getFileObjInDirectory("file3");
        // get the file content
        String output = file.toString();
        assertEquals("file3", output);
        // check their is no standard output
        assertTrue(retType.contains(null));
    }
}
