public class SynchQueue<T> {
    T[] data;
    int last;
    boolean done;

    public SynchQueue(){
        data = (T[]) new Object[10];
        last = -1;
        done = false;
    }

    public boolean enqueue(T value){
        if(isFull()){
            return false;
        }
        else{
            last++;
            data[last] = value;
            return true;
        }
    }

    public T dequeue(){

        if(isEmpty()){
            return null;
        }
        else {
            T value = data[0];
            //Shift data to the left to fill the empty space of frontIndex
            for (int i = 0; i < data.length; i++) {
                if ((i + 1) < data.length) {
                    data[i] = data[i + 1];
                    data[i + 1] = null;
                }
            }
            last--;
            return value;
        }
    }

    public int size(){
        return last+1;
    }

    public boolean isFull(){
        return last == 9;
    }

    public boolean isEmpty(){
        return last == -1;
    }

    public boolean isDone(){
        return done;
    }

    public void shutdown(){
        done = true;
    }

    @Override
    public String toString() {

        String result = "";
        for(int i=0; i<data.length; i++){

            result += data[i]+" ";
        }

        return result;
    }
}
