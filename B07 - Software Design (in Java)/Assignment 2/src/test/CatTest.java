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

public class CatTest {
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
    // get current directory
    Directory dir = (Directory) JShellFileSystem.getCurrentDir();
    // create a new file with content
    dir.add(new File("file", dir, "line 1"));
    // input arguments
    List<String> args = new ArrayList<String>(Arrays.asList("cat", "file"));
    // initialize cat command
    Cat cat = new Cat();
    // execute cat command
    cat.execute(args);
    // get the specified file
    File file = (File) dir.getFileObjInDirectory("file");
    // get the file content
    String output = file.toString();
    assertEquals("line 1", output);
  }

  @Test
  public void testValidate() {
    // test invalid arguments error
    List<String> args = new ArrayList<String>(Arrays.asList("cat"));
    Cat cat = new Cat();
    assertEquals("JShell: cat: Too many, or too few arguments: cat ",
        cat.execute(args).get(0).toString());
  }
}
