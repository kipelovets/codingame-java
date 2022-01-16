package ru.kipelovets.shadows_of_the_knight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AppTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void shouldAnswerWithTrue(String input, String expectedOutput) {
        var streamMock = new StreamMock(input);
        try {
            var thread = new PlayerThread();
            thread.start();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            streamMock.finish();
        }

        var out = streamMock.getOutput();

        assertEquals(expectedOutput.replaceAll("\n", "\r\n"), out.trim());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of("""
            100 100 7 5 98 
            UL 
            UL 
            UL 
            UL 
            UL
            UL 
            UL""", """
            2 49
            1 24
            0 12
            0 6
            0 3
            0 1
            0 0""")
        );
    }
}
