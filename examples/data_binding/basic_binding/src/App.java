import javafx.beans.property.*;

public class App {
    public static void main(String[] args) {

        IntegerProperty num1 = new SimpleIntegerProperty(10);
        IntegerProperty num2 = new SimpleIntegerProperty(5);
        
        System.out.println("num1 = " + num1.get());
        System.out.println("num2 = " + num2.get());
        System.out.println("Setting num1 = 20");
        num1.set(20);
        System.out.println("num1 = " + num1.get());

        System.out.println("Binding num1 to get its value from num2");
        num1.bind(num2);
        System.out.println("num1 = " + num1.get());
        System.out.println("Setting num2 = 30");
        num2.set(30);
        System.out.println("num1 = " + num1.get());


    }
}