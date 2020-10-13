import java.util.*;

public class CaptureDemo {

    static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();

        names.add("Frank");
        names.add("Joe");
        names.add("");
        names.add("Reshi");

        String name = "";
        do {
            System.out.print("Enter name to find:");
            name = scan.nextLine();

            final String name2find = name;   // define final local so it can be accessed in lambda

            int loc = find(names, v -> v.equals(name2find) );  // Cannot access name; must use name2find
            System.out.println("Found " + name + " at position: " + loc);
        } while (name.length() != 0);

    }

    static int find(List<String> items, MyPredicate predicate) {
        int i = 0;
        while (i < items.size()) {
            String item = items.get(i);
            if (predicate.test(item)) {
                return i;
            }
            ++i;
        }

        return -1;
    }
}

interface MyPredicate {
    boolean test(String value);
}
