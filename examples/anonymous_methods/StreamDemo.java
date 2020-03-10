import java.util.*;
import java.util.stream.Collectors;

class Program {

    static boolean isLarge(String s) {
        if (s.length() > 3)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        var items = new ArrayList<String>(Arrays.asList("RED", "green", "blue", ""));

        String result = "";
        for (String item : items) {
            if (item.length() > 3) {
                result = item;
                break;
            }
        }

        result = items.stream().filter(Program::isLarge).findFirst().get();

        result = items.stream().filter((String s) -> s.length() > 3).findFirst().get();

        System.out.println("Found item length > 3: " + result);

        items.removeIf(s -> s == null || s.length() == 0);

        List<String> found = items.stream().filter(Program::isLarge).collect(Collectors.toList());
        System.out.println("\nItems for which isLarge() is true:");
        found.stream().forEach(System.out::println);

        var students = new ArrayList<Student>(
                Arrays.asList(new Student("Steve", 15), new Student("George", 18), new Student("Amy", 16)));

        students.sort((Student stu1, Student stu2) -> stu1.age - stu2.age);

        System.out.println("\nStudents sorted by age:");
        students.stream().forEach(System.out::println);

    }

}

class Student {
    public String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }

}
