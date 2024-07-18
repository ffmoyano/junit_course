package org.ffmoyano;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math operations in Calculator class")
class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setup() {  // must be static
        System.out.println("Executing @BeforeAll method");
    }

    @AfterAll
    static void cleanup() {  // must be static
        System.out.println("Executing @AfterAll method");
    }

    @BeforeEach
    void beforeEachTestMethod() {
        System.out.println("Executing @BeforeEach method");
        calculator = new Calculator();
    }

    @AfterEach
    void afterEachTestMethod() {
        System.out.println("Executing @AfterEach method");
    }

    @DisplayName("Test 4/2 = 2")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        // Arrange - Given
        int dividend = 4;
        int divisor = 2;
        int expected = 2;
        // Act - When
        int result = calculator.integerDivision(dividend, divisor);
        // Assert - Then
        assertEquals(expected, result, "4/2 did not produce 2");
    }

    // @Disabled("Not yet implemented")
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenFourIsDividedByZero_ShouldThrowArithmeticException() {
        System.out.println("Running division by zero");
        // Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";
        // Act
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.integerDivision(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");
        // Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");
    }

    @DisplayName("Test integer substraction methodsource [minuend, subtrahend, expected]")
    @ParameterizedTest // accepts parameters
    @MethodSource("integerSubtractionInputParameters")
        // method whence it will take the parameters, if the method source and this have the same name this can be left empty
    void integerSubtractionByMethodSource(int minuend, int subtrahend, int expected) {
        int result = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(result, expected, () -> minuend + " - " + subtrahend + " did not produce " + expected);
    }

    private static Stream<Arguments> integerSubtractionInputParameters() {
        return Stream.of(
                // the integerSubstraction Method will run one time for each line of Arguments.of provided here
                Arguments.of(33, 1, 32),
                Arguments.of(54, 1, 53),
                Arguments.of(24, 1, 23)
        );
    }

    @DisplayName("Test integer substraction csvsource [minuend, subtrahend, expected]")
    @ParameterizedTest // accepts parameters
    @CsvSource({"33,1,32", "54, 1, 53"})
    void integerSubtractionByCsvParams(int minuend, int subtrahend, int expected) {
        int result = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(result, expected, () -> minuend + " - " + subtrahend + " did not produce " + expected);
    }

    @DisplayName("Test integer substraction csvfilesource [minuend, subtrahend, expected]")
    @ParameterizedTest // accepts parameters
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void integerSubtractionByCsvFile(int minuend, int subtrahend, int expected) {
        int result = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(result, expected, () -> minuend + " - " + subtrahend + " did not produce " + expected);
    }

    @ParameterizedTest
    // it will run three times, one with each parameter provided with strings. It accepts other types
    @ValueSource(strings = {"John", "Kate", "Alice"})
    void valueSourceDemonstration(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }
}