
public class AddArgs {

    public static void main(String[] args) {
        int num1 = 0, num2 = 0;
        try {
            num1 = Integer.parseInt(args[0]);
            num2 = Integer.parseInt(args[1]);
            System.out.println("The sum is: " + (num1 + num2));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        

    }

}