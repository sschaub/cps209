
public class AddArgsOO {

    public static void main(String[] args) {
        int num1 = 0, num2 = 0;
        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[1]);
            var adder = new Adder(num1, num2);
            int sum = adder.addEm();
            System.out.println("The sum is: " + sum);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        

    }

}