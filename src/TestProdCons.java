/**
 * TestProdCons class
 *
 * @author Hieu Duong
 * @date 03/05/2018
 */


import java.util.Scanner;

public class TestProdCons {
    public static void main(String[] args){
        SynchQueue sq = new SynchQueue();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter Produce count and Consumer count: ");
        String value = keyboard.nextLine();
        String[] command;
        try{
            command = value.split(" ");
            if(command.length!=2){
                System.out.println("Input is not correct. System terminated!");
                System.exit(1);
            }
            else {
                int producerCount = 0;
                try{
                    producerCount = Integer.parseInt(command[0]);
                }catch (Exception ex){
                    System.out.println("Unable to get command. System terminated!");
                    System.exit(1);
                }

                if(producerCount==0){
                    System.out.println("Input must be integer and larger than 0.");
                    System.exit(1);
                }

                int consumerCount = 0;
                try{
                    consumerCount = Integer.parseInt(command[1]);
                }catch (Exception ex){
                    System.out.println("Unable to get command. System terminated!");
                    System.exit(1);
                }

                if(consumerCount==0){
                    System.out.println("Input must be integer and larger than 0.");
                    System.exit(1);
                }

                startThreads(producerCount, consumerCount, sq);
            }
        }
        catch(Exception ex){
            System.out.println("Input is not correct. System terminated!");
            System.exit(1);
        }
    }

    /**
     * Method to inttialize and start threads
     * @param producerCount
     * @param consumerCount
     * @param sq
     */
    public static void startThreads(int producerCount, int consumerCount, SynchQueue sq) {

        //Create an array to hold producer threads
        Thread[] producerThreads = new Thread[producerCount];
        for(int index=0; index<producerCount; index++){
            ProduceThread pt = new ProduceThread((index+1), sq);
            producerThreads[index] = pt;
            pt.start();
        }

        //Create an array to hold consumer threads
        Thread[] consumerThreads = new Thread[consumerCount];
        for(int index=0; index<consumerCount; index++){
            ConsumerThread ct = new ConsumerThread((index+1), sq);
            consumerThreads[index] = ct;
            ct.start();
        }

        //Join producer thread to main thread
        for (Thread ptThread : producerThreads) {
            try {
                ptThread.join();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        //Join consumer thread to main thread
        for (Thread ctThread : consumerThreads) {
            try {
                ctThread.join();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("Main program exiting after joining threads.");
    }
}
