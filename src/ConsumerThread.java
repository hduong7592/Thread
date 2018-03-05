import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.security.SecureRandom;

public class ConsumerThread extends Thread {
    private int index;
    private SynchQueue sq;

    public ConsumerThread(int index, SynchQueue sq) {
        this.index = index;
        this.sq = sq;
    }

    public void run(){
        System.out.println("Consumer "+index+" started.");

        try{
            this.sleep(6000);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        while (!sq.isEmpty()) {
            if(!sq.isDone()) {
                int number;
                try {
                    number = (int) sq.dequeue();
                    System.out.println("Consumer " + index + " removed: " + number + ", computed " + number + "!: " + computeFactorial(number, number - 1));
                    try {
                        int sleepTime = (new SecureRandom()).nextInt(1000);
                        this.sleep(sleepTime);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public int computeFactorial(int result, int n){
        if(n==0) return result;
        else{
            return computeFactorial(result*n, n-1);
        }
    }
}
