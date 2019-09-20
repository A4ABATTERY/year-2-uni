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

public class ChangeDirectoryTest {
  @Before
  // initialize JShell system
  public void initialize() {
    JShellFileSystem.initJShellFileSystem(new Directory("/", null));
  }

  @After
  public void restore() {
    // as JShell system only initialize once, so restore back the current
    // directory to root, clean the content in directory in order to run the
    // whole test suite at once
    JShellFileSystem.setCurrentDir((Directory) JShellFileSystem.getRootDir());
    ((Directory) JShellFileSystem.getRootDir())
        .setDirectoryContents(new ArrayList<FileObject>());
  }

  @Test
  public void testExecute() {
    // get root directory
    Directory root = (Directory) JShellFileSystem.getRootDir();
    // setup new directories
    Directory test1 = new Directory("test1", root);
    root.add(test1);
    Directory test2 = new Directory("test2", test1);
    test1.add(test2);
    // input arguments for relative path
    List<String> args1 = new ArrayList<String>(Arrays.asList("cd", "test1"));
    // input arguments for full path
    List<String> args2 =
        new ArrayList<String>(Arrays.asList("cd", "/test1/test2"));
    // initialize cd command
    ChangeDirectory cd = new ChangeDirectory();
    // execute cd command
    cd.execute(args1);
    // check whether the working directory has changed by relative path
    assertEquals(test1, JShellFileSystem.getCurrentDir());
    // execute cd command
    cd.execute(args2);
    // check whether the working directory has changed by full path
    assertEquals(test2, JShellFileSystem.getCurrentDir());
  }

  @Test
  public void testPathNotFoundError() {
    // cd to non-existing path
    List<String> args = new ArrayList<String>(Arrays.asList("cd", "fakePath"));
    ChangeDirectory cd = new ChangeDirectory();
    assertEquals("JShell: Path not found: \"fakePath\" was not found",
        cd.execute(args).get(0).toString());
  }
}
