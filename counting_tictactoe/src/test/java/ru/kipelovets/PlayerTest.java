package ru.kipelovets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void shouldAnswerWithTrue(String input, String expectedOutput) {
        // var streamMock = new StreamMock(input);
        // try {
        //     var thread = new PlayerThread();
        //     thread.start();
        //     Thread.sleep(100);
        // } catch (InterruptedException e) {
        //     System.err.println(e.getMessage());
        // } finally {
        //     streamMock.finish();
        // }

        // var out = streamMock.getOutput();

        // assertEquals(expectedOutput.replaceAll("\n", "\r\n"), out.trim());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of("""
            1
            2""", """
            3
            4""")
        );
    }
}
