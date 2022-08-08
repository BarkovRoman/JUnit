import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String line = "1 2 3 +";

        int result = calculator.calculatePolishNotation(line);

        assertEquals(5, result, "Задачи не соответствуют");
    }

    @Test
    public void shouldCalculateSubtraction() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String line = "1 2 3 -";

        int result = calculator.calculatePolishNotation(line);

        assertEquals(-1, result, "Задачи не соответствуют");
    }

    @Test
    public void shouldCalculateMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String line = "1 2 3 *";

        int result = calculator.calculatePolishNotation(line);

        assertEquals(6, result, "Задачи не соответствуют");
    }

    @Test
    public void shouldCalculateMultiplicationPass() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String line = "1   3 *";

        int result = calculator.calculatePolishNotation(line);

        assertEquals(3, result, "Задачи не соответствуют");
    }

    @Test
    public void shouldCalculateEmptyString()  {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        String line = " ";

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () ->   {
                    calculator.calculatePolishNotation(line);
                }, "Список пустой"
        );
        assertNull(exception.getMessage());
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
