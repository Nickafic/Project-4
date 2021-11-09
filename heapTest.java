import java.io.*;
public class heapTest {
    public static void main(String[] args) throws IOException{
        MaxHeap<String> testHeap = new MaxHeap<>();
        testHeap.optimalMethod("data_sorted.txt");
    }
}
