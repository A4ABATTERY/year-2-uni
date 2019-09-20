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

public class PushDirectoryTest {

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
		// input arguments
		List<String> args1 =
				new ArrayList<String>(Arrays.asList("pushd", "/test1"));
		List<String> args2 =
				new ArrayList<String>(Arrays.asList("pushd", "/test2"));
		// initialize pushd command
		PushDirectory pushd = new PushDirectory();
		// execute pushd command
		pushd.execute(args1);
		// check whether the working directory has changed to the new one
		assertEquals(test1, JShellFileSystem.getCurrentDir());
		// check whether the old working directory has pushed to the stack
		assertEquals(root, JShellFileSystem.getDirectoryStack().pop());
		// execute pushd command
		pushd.execute(args2);
		// check whether the working directory has changed to the new one
		assertEquals(test2, JShellFileSystem.getCurrentDir());
		// check whether the old working directory has pushed to the stack
		assertEquals(test1, JShellFileSystem.getDirectoryStack().pop());
	}

	@Test
	public void testValidate() {
		// test invalid arguments error
		List<String> args = new ArrayList<String>(Arrays.asList("pushd"));
		PushDirectory pushd = new PushDirectory();
		assertEquals("JShell: pushd: Too many, or too few arguments: pushd ",
				pushd.execute(args).get(0).toString());
	}
}
