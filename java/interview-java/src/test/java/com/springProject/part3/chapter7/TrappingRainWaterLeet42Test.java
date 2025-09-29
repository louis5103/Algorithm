package com.springProject.part3.chapter7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;


class TrappingRainWaterLeet42Test {
    static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6),
                Arguments.of(new int[]{4,2,0,3,2,5}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testTwoPointerMethod(int[] input, int expectedOutput) throws IOException {
        int actualOutput = new TrappingRainWaterLeet42(input).twoPointerMethod();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testStackMethod(int[] input, int expectedOutput) throws IOException {
        int actualOutput = new TrappingRainWaterLeet42(input).stackMethod();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}