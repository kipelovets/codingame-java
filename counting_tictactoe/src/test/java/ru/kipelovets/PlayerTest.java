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
        // Player.main(null);
        // streamMock.finish();

        // var out = streamMock.getOutput();

        // assertEquals(expectedOutput.replaceAll("\n", "\r\n"), out.trim());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("""
                        -1 -1
                        1
                        0 0""", """
                        0 0"""));
    }
}
