package org.ffmoyano;


import org.junit.jupiter.api.*;

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
        String expectedExceptionMessage="/ by zero";
        // Act
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.integerDivision(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");
        // Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");
    }

    @DisplayName("Test 33 - 1 ) 32")
    @Test
    void integerSubtraction() {
        int result = calculator.integerSubtraction(33, 1);
        assertEquals(32, result);
    }

}