
import java.io.*;
public class heapTest {
    public static void main(String[] args) throws IOException{
        try{
            MaxHeap<String> testHeap = new MaxHeap<>(105);
            testHeap.optimalMethod("data_sorted.txt");
        }
        catch(IOException e){
            System.out.println("Error While Reading");
        }

    }
}
