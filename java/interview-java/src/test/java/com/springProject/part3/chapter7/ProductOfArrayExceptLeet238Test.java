package com.springProject.part3.chapter7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductOfArrayExceptLeet238Test {
    static Stream<Arguments> provideTestData(){
        return Stream.of(
                Arguments.of(new int[] {1,2,3,4}, new int[] {24,12,8,6}),
                Arguments.of(new int[] {-1,1,0,-3,3}, new int[] {0,0,9,0,0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    public void testProductExceptSelf(int[] input, int[] expected) {
        int[] actual = new ProductOfArrayExceptLeet238(input).productExceptSelf();
        Assertions.assertEquals(expected, actual);
    }
}