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

public class MakeDirectoryTest {
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
    Directory curr = (Directory) JShellFileSystem.getCurrentDir();
    // input arguments for relative path
    List<String> args1 = new ArrayList<String>(Arrays.asList("mkdir", "test1"));
    // input arguments for full path
    List<String> args2 =
        new ArrayList<String>(Arrays.asList("mkdir", "/test1/test2"));
    // initialize mkdir command
    MakeDirectory mkdir = new MakeDirectory();
    // execute mkdir command
    mkdir.execute(args1);
    // check whether the directory has created in relative path
    assertTrue(curr.getFileObjInDirectory("test1") != null);
    // execute mkdir command
    mkdir.execute(args2);
    Directory test1 = (Directory) curr.getFileObjInDirectory("test1");
    // check whether the directory has created in full path
    assertTrue(test1.getFileObjInDirectory("test2") != null);
  }

  @Test
  public void testPathNotFoundError() {
    // cd to non-existing path
    List<String> args =
        new ArrayList<String>(Arrays.asList("mkdir", "/fakepath/folder"));
    MakeDirectory mkdir = new MakeDirectory();
    assertEquals("JShell: Path not found: \"/fakepath/\" was not found",
        mkdir.execute(args).get(0).toString());
  }
}
