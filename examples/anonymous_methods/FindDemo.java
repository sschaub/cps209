import java.util.ArrayList;
import java.util.List;

public class FindDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();

        names.add("Frank");
        names.add("Joe");
        names.add("");
        names.add("Reshi");

        int loc = find(names, new MatchEmpty() );
        System.out.println("Found empty string at position: " + loc);

        loc = find(names, new MatchThree());
        System.out.println("Found name with at least 3 characters at position: " + loc);

        loc = find(names, v -> v.length() == 0 );
        System.out.println("Found empty string at position: " + loc);

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

class MatchEmpty implements MyPredicate {

    @Override
    public boolean test(String value) {
        return value.equals("");
    }

}

class MatchThree implements MyPredicate {

    @Override
    public boolean test(String value) {
        return value.length() >= 3;
    }

}