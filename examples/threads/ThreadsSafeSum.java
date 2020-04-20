
public class ThreadsSafeSum {

    public static void main(String[] args) throws Exception {
        var t1 = new SumThread(1, 100000);
        t1.start();

        var t2 = new SumThread(100001, 200000);
        t2.start();
        
        t1.join(); // Wait for t1 to finish
        t2.join(); // Wait for t2 to finish
        long sum = t1.sum + t2.sum;
        System.out.println("sum = " + sum);
    }

}

class SumThread extends Thread {
    int start, end;
    long sum;

    public SumThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; ++i)
            sum += i;
    }
}