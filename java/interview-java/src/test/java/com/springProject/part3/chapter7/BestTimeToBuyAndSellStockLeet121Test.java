package com.springProject.part3.chapter7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class BestTimeToBuyAndSellStockLeet121Test {
    static Stream<Arguments> proveTestData(){
        return Stream.of(
                Arguments.of(new int[] {7,1,5,3,6,4}, 5),
                Arguments.of(new int[] {7,6,4,3,1}, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("proveTestData")
    void solutionTest(int[] input, int expected){
        int actual = BestTimeToBuyAndSellStockLeet121.solution(input);
        Assertions.assertEquals(expected, actual);
    }

}