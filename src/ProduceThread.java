/**
 * ProduceThread class
 *
 * @author Hieu Duong
 * @date 03/05/2018
 */


import java.security.SecureRandom;

public class ProduceThread extends Thread {
    private int index;
    private SynchQueue sq;
    private Object lock;

    public ProduceThread(int index, SynchQueue sq) {
        this.index = index;
        this.sq = sq;
        lock = new Object();
    }

    public void run(){
        System.out.println("Producer "+index+" started.");

        try{
            this.sleep(30000);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        synchronized (lock) {
            while (!sq.isDone()) {
                int randomNumber = (new SecureRandom()).nextInt(10) + 1;
                if (sq.enqueue(randomNumber)) {
                    System.out.println("Producer " + index + " added: " + randomNumber + ", queue size: " + sq.size());
                    try {
                        int sleepTime = (new SecureRandom()).nextInt(1000);
                        this.sleep(sleepTime);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    sq.shutdown();
                }
            }
        }
    }
}
