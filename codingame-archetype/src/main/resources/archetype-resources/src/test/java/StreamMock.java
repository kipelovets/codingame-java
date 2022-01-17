package $package;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class StreamMock {
  PrintStream originalOut;
  InputStream originalIn;

  ByteArrayOutputStream outStream;

  public StreamMock(String input) {
    originalOut = System.out;
    outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));

    originalIn = System.in;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
  }

  public void finish() {
    System.out.flush();
    System.setOut(originalOut);

    System.setIn(originalIn);
  }

  public String getOutput() {
    return outStream.toString();
  }
}
