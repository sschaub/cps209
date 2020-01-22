/*
 * This class contains the main method of the application
 */

public class Calculator {

    private int current;

    public Calculator() {
        current = 0;
    }

    public Calculator(int current) {
        this.current = current;
    }

    public void setCurrent(int newCurrent) {
        this.current = newCurrent;
    }

    public int getCurrent() {
        return current;
    }

    public void add(int num) {
        if (num > 0 && current > 0 && current + num < 0) {
            throw new IllegalArgumentException("Argument too large");
        }
        current += num;
    }
}
