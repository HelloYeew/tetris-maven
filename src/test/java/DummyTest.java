import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.awt.Desktop;
import java.net.URI;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DummyTest {
    @BeforeEach
    void setUp() {
        // Setup something here
    }

    @Test
    @DisplayName("Normal assertion use")
    @Order(1)
    void testAssert() {
        assertEquals(20, Calculator.multiply(2,10));
    }

    @RepeatedTest(5) // This test will be repeated 5 times
    @DisplayName("Test multiple time")
    @Order(2)
    void testMultiplyWithZero() {
        assertEquals(0, Calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, Calculator.multiply(5, 0), "Multiple with zero should be zero");
    }

    @Test
    @DisplayName("Exception test")
    @Order(3)
    void testException() {
        // Create an exception instance and pass it to the assertThrows() method
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Calculator.divide(1, 0);
        });
        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Multiple assertion test")
    @Order(4)
    void testMultipleAssertion() {
        // In a multiple assertion test, if any assertion fails, the test will fail.
        // But we can ensure that all test will be executed
        assertAll("sum",
            () -> assertEquals(5, Calculator.add(2, 3)),
            () -> assertEquals(5, Calculator.add(3, 2)),
            () -> assertEquals(100000, Calculator.add(99999, 1)),
            () -> assertEquals(500, Calculator.factorial(5)),
            () -> assertEquals(200, Calculator.factorial(5))
        );
    }

    @Test
    @DisplayName("Test with timeout")
    @Order(5)
    void testWithTimeout() {
        // This test will fail because the timeout is 100ms and the method will take 1000ms to execute
        assertTimeout(Duration.ofMillis(100), () -> {
            return Calculator.lowRamAdd(1, 2);
        });
    }

    @Test
    @DisplayName("Force quit on timeout test")
    @Order(6)
    void testWithTimeoutPreemptively() {
        // This test will fail because the timeout is 100ms and the method will take 1000ms to execute
        // But this test will run only 100ms and then force quit the test
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            return Calculator.lowRamAdd(1, 2);
        });
    }

    @Test
    @DisplayName("Disabled test")
    @Disabled("I don't want to test this method anymore")
    @Order(7)
    void testDisabled() {
        assertTrue(Calculator.rickRolled());
    }

    @TestFactory
    @DisplayName("Dynamic test")
    @Order(8)
    Stream<DynamicTest> testDifferentMultiplyOperations() {
        int[][] data = new int[][] { { 1, 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
        return Arrays.stream(data).map(entry -> {
            int m1 = entry[0];
            int m2 = entry[1];
            int expected = entry[2];
            return dynamicTest(m1 + " * " + m2 + " = " + expected, () -> {
                assertEquals(expected, Calculator.multiply(m1, m2));
            });
        });
    }

    public static int[][] data() {
        return new int[][] { { 1, 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    @DisplayName("Parameterized test")
    @Order(9)
    void testWithStringParameter(int[] data) {
        int m1 = data[0];
        int m2 = data[1];
        int expected = data[2];
        assertEquals(expected, Calculator.multiply(m1, m2));
    }

    void tearDown() {
        // Tear down after test
    }
}

/**
 * A simple calculator class for testing purpose.
 */
class Calculator {
    /**
     * Add two numbers
     * @param a The first number
     * @param b The second number
     * @return The sum of the two numbers
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Substract two numbers
     * @param a The first number
     * @param b The second number
     * @return The result of the substraction
     */
    public static int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiply two numbers
     * @param a The first number
     * @param b The second number
     * @return The result of the multiplication
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divide two numbers
     * @param a The dividend
     * @param b The divisor
     * @return The result of the division
     */
    public static int divide(int a, int b) {
        return a / b;
    }

    /**
     * Return the factorial of a number
     * @param n The number to calculate factorial
     * @return The factorial of the number
     */
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Mock the heavy computation on add function
     * @param a The first number
     * @param b The second number
     * @return The sum of a and b
     */
    public static int lowRamAdd(int a, int b) {
        // sleep for 1 second to mock that your function is doing some heavy computation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }

    /**
     * This calculator can rick rolled you! (Thanks to my copilot for this method!)
     */
    public static boolean rickRolled() {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
