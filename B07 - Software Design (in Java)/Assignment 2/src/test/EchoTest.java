package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import JShellReturnTypes.RetType;
import commands.*;

public class EchoTest {
  // mock object for standard output return type
  class MockRetType implements RetType {
    String input;

    MockRetType(String input) {
      this.input = input;
    }

    @Override
    public String toString() {
      return input;
    }
  }

  @Test
  public void testExecute() {
    // input argument
    List<String> args1 = new ArrayList<String>(Arrays.asList("echo", "test"));
    List<String> args2 =
        new ArrayList<String>(Arrays.asList("echo", "test with space"));
    // standard output mock object declaration
    MockRetType mockRetType1 = new MockRetType("test");
    MockRetType mockRetType2 = new MockRetType("test with space");
    Echo echo = new Echo();
    assertEquals(mockRetType1.toString(),
        echo.execute(args1).get(0).toString());
    assertEquals(mockRetType2.toString(),
        echo.execute(args2).get(0).toString());
  }

  @Test
  public void testValidate() {
    // test invalid arguments error
    List<String> args = new ArrayList<String>(Arrays.asList("echo"));
    MockRetType mockRetType =
        new MockRetType("JShell: echo: Too many, or too few arguments: echo ");
    Echo echo = new Echo();
    assertEquals(mockRetType.toString(), echo.execute(args).get(0).toString());
  }
}
