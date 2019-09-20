package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import commands.*;

public class ShellHistoryTest {
	@Test
	public void testExecute() {
		// initialize history command
		ShellHistory history = new ShellHistory();
		// append command into history log
		ShellHistory.appendCmdEntered("command1");
		ShellHistory.appendCmdEntered("command2");
		// input argument
		List<String> args1 = new ArrayList<String>(Arrays.asList("history"));
		List<String> args2 =
				new ArrayList<String>(Arrays.asList("history", "1"));
		// test to retrieve all history
		assertEquals(" 1  command1 \n 2  command2 ",
				history.execute(args1).get(0).toString());
		// test to retrieve lastest N number of history
		assertEquals(" 2  command2 ", history.execute(args2).get(0).toString());

	}

	@Test
	public void testValidate() {
		// test invalid arguments error
		List<String> args =
				new ArrayList<String>(Arrays.asList("history", "123", "456"));
		ShellHistory history = new ShellHistory();
		assertEquals(
				"JShell: history: Too many, or too few arguments: history 123 "
				+ "456 ",
				history.execute(args).get(0).toString());
	}
}
