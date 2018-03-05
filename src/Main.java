import java.security.SecureRandom;

public class Main {
    public static void main(String[] args){
        SynchQueue<Integer> sq = new SynchQueue<>();

        boolean isNotDone = true;
        while(isNotDone){
            int randomNumber = (new SecureRandom()).nextInt(10) + 1;
            if(sq.enqueue(randomNumber)){
                System.out.println("Added "+randomNumber);
            }
            else{
                isNotDone = false;
            }
        }


        System.out.println(sq);
    }
}
