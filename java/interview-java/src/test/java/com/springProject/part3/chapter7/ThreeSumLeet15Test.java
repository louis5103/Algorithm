package com.springProject.part3.chapter7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


class ThreeSumLeet15Test {
    static Stream<Arguments> provideTestData() {
        return Stream.of(
                // Test Data 1.
                Arguments.of(
                        new int[] {-1,0,1,2,-1,-4},
                        List.of(
                                List.of(-1,-1,2),
                                List.of(-1,0,1)
                        )
                ), // Test Data 2.
                Arguments.of(
                        new int[] {0,1,1},
                        List.of()
                ), // Test Data 3.
                Arguments.of(
                        new int[] {0,0,0},
                        List.of(
                                List.of(0,0,0)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testTwoPointerMethod(int[] input, List<List<Integer>> expectedOutput) throws IOException {
        List<List<Integer>> actualOutput = new ThreeSumLeet15(input).twoPointerMethod();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}