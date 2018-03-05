public class Worker1 extends Thread {
    public void run(){
        //System.out.println("Worker thread");
        String value = "";
        for(int i=0; i<50; i++){
            value+="*";
            System.out.println(value);
        }
    }

    public static void main(String[] args){
        Thread runner = new Worker1();
        runner.start();

        //System.out.println("Runner Thread");


    }
}
