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

public class ListDirectoryTest {
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
		// setup new directories
		Directory test1 = new Directory("test1", root);
		root.add(test1);
		Directory test2 = new Directory("test2", test1);
		test1.add(test2);
		// set up new file
		File file1 = new File("file1", root);
		File file2 = new File("file2", test1);
		root.add(file1);
		test1.add(file2);
		// initialize ls command
		ListDirectory ls = new ListDirectory();
		// input arguments for just ls
		List<String> args1 = new ArrayList<String>(Arrays.asList("ls"));
		assertEquals("test1\nfile1", ls.execute(args1).get(0).toString());
		// input arguments for ls [relative path]
		List<String> args2 =
				new ArrayList<String>(Arrays.asList("ls", "test1"));
		assertEquals("/test1:\n\ttest2\n\tfile2",
				ls.execute(args2).get(0).toString());
		// input arguments for ls [full path]
		List<String> args3 = new ArrayList<String>(Arrays.asList("ls", "/"));
		assertEquals("/:\n\ttest1\n\tfile1",
				ls.execute(args3).get(0).toString());
		// input arguments for ls [file]
		List<String> args4 =
				new ArrayList<String>(Arrays.asList("ls", "/test1/file2"));
		assertEquals("/test1/file2", ls.execute(args4).get(0).toString());
		// input arguments for ls -R
		List<String> args5 = new ArrayList<String>(Arrays.asList("ls", "-R"));
		assertEquals("/:\n\tfile1\n\ttest1:\n\t\tfile2\n\t\ttest2:",
				ls.execute(args5).get(0).toString());
	}

	@Test
	public void testFileObjectNotFoundError() {
		// list non-existing FileObject
		List<String> args =
				new ArrayList<String>(Arrays.asList("ls", "fakeFileObject"));
		ListDirectory ls = new ListDirectory();
		assertEquals(
				"JShell: File or Directory not Found: \"" + "fakeFileObject"
						+ "\" does not exist",
				ls.execute(args).get(0).toString());
	}
}
