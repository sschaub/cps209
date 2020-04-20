
public class ThreadsDemo
{
    public static void main(String[] args) throws Exception {
        var t1 = new Thread(() -> Go());
        t1.start();

        var t2 = new Thread(() -> Go());
        t2.start();
        
        t1.join(); // Wait for t1 to finish
        t2.join(); // Wait for t2 to finish
        System.out.println("All done!");
    }

    static void Go()
    {
        for (int i = 0; i < 500; ++i)
            System.out.println(i);
    }
}