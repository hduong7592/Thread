/**
 * ConsumerThread class
 *
 * @author Hieu Duong
 * @date 03/05/2018
 */


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
            this.sleep(30000);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        synchronized (lock) {
            while (!sq.isDone()) {
                int number = sq.dequeue();
                if(number >=0 ){
                    System.out.println("Consumer " + index + " removed: " + number + ", computed " + number + "!: " + computeFactorial(number));
                    try {
                        int sleepTime = (new SecureRandom()).nextInt(1000);
                        this.sleep(sleepTime);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else{
                    System.out.println("Nothing in queue. Size: "+sq.size());
                }
            }
        }

    }

    /**
     * Recursion method to compute factorial
     * @param n
     * @return
     */
    public int computeFactorial(int n){
        if(n<=1) return 1;
        else{
            return n*computeFactorial( n-1);
        }
    }
}
