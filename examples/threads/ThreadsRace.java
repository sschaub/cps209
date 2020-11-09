
public class ThreadsRace {

    static long sum;

    public static void main(String[] args) throws Exception {
        var t1 = new Thread(() -> Sum(1, 100000));
        t1.start();

        var t2 = new Thread(() -> Sum(100001, 200000));
        t2.start();
        
        t1.join(); // Wait for t1 to finish
        t2.join(); // Wait for t2 to finish
        System.out.println("sum = " + sum);
    }

    static void Sum(int start, int end)
    {
        for (int i = start; i <= end; ++i) {
            sum += i;
        }

    }
}