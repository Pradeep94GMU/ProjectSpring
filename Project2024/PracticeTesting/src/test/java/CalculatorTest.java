// CalculatorTest.java
import org.example.CalMultiply;
import org.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculatorTest {

    Calculator calculator;

    CalMultiply calMultiply;
    //before testing we need to setup something which will help to use external service
    @BeforeEach
    void setUp() {
        //this method will run before any method method in this method
        //we are using CalMultiple service here so we can use mock of the
        //to create mock of that we can Mockito class
        calMultiply = Mockito.mock(CalMultiply.class);

        //how to use this mock obj
        calculator = new Calculator(calMultiply); //

    }

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    void testMultiplication() {

        when(calMultiply.multiply(2, 3)).thenReturn(6);
        //to check this below expected behaviour , we can actually need to defien the beh first
        assertEquals(6, calculator.multiply(2, 3));

        //now just to make sure that, we got the mock obj , we need to verify
        verify(calMultiply, times(1)).multiply(2,3);

    }

    @Test
    void testDivision() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(1, 0);
        });
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}

