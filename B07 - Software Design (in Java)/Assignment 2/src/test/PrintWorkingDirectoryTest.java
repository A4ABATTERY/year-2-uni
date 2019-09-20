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

public class PrintWorkingDirectoryTest {
	@Before
	// initialize JShell system
	public void initialize() {
		JShellFileSystem.initJShellFileSystem(new Directory("/", null));
	}

	@After
	public void restore() {
		// as JShell system only initialize once, so restore back the current
		// directory to root for the whole test suite
		JShellFileSystem
				.setCurrentDir((Directory) JShellFileSystem.getRootDir());
	}

	@Test
	public void testExecute() {
		// input argument
		List<String> args = new ArrayList<String>(Arrays.asList("pwd"));
		// declare pwd command
		PrintWorkingDirectory pwd = new PrintWorkingDirectory();
		assertEquals("/", pwd.execute(args).get(0).toString());
		// change working directory
		JShellFileSystem.setCurrentDir(new Directory("test",
				(Directory) JShellFileSystem.getRootDir()));
		assertEquals("/test", pwd.execute(args).get(0).toString());
	}
}
