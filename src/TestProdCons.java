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

                Thread[] producerThreads = new Thread[producerCount];
                for(int index=0; index<producerCount; index++){
                    ProduceThread pt = new ProduceThread((index+1), sq);
                    producerThreads[index] = pt;
                    pt.start();
                }

                int consumerCount = 0;
                try{
                    consumerCount = Integer.parseInt(command[1]);
                }catch (Exception ex){
                    System.out.println("Unable to get command. System terminated!");
                    System.exit(1);
                }

                Thread[] consumerThreads = new Thread[consumerCount];
                for(int index=0; index<consumerCount; index++){
                    ConsumerThread ct = new ConsumerThread((index+1), sq);
                    consumerThreads[index] = ct;
                    ct.start();
                }

                for (Thread ptThread : producerThreads) {
                    ptThread.join();
                }

                for (Thread ctThread : consumerThreads) {
                    ctThread.join();
                }

                System.out.println("All done");
            }
        }
        catch(Exception ex){
            System.out.println("Input is not correct. System terminated!");
            System.exit(1);
        }
    }
}
