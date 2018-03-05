import java.security.SecureRandom;

public class ConsumerThread extends Thread {
    private int index;
    private SynchQueue sq;
    private Object lock;

    public ConsumerThread(int index, SynchQueue sq) {
        this.index = index;
        this.sq = sq;
        lock = new Object();
    }

    public void run(){
        System.out.println("Consumer "+index+" started.");

        try{
            this.sleep(4000);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        boolean isNotDone = true;
        synchronized (lock) {
            while (isNotDone) {
                int number = (int) sq.dequeue();
                //System.out.println("Dequeue");
                if(number >=0 ){
                    System.out.println("Consumer " + index + " removed: " + number + ", computed " + number + "!: " + computeFactorial(number));
                    try {
                        int sleepTime = (new SecureRandom()).nextInt(1000);
                        this.sleep(sleepTime);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                isNotDone = sq.isDone();
            }
        }

    }

    public int computeFactorial(int n){
        if(n<=1) return 1;
        else{
            return n*computeFactorial( n-1);
        }
    }
}
