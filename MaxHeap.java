import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public final class MaxHeap<T extends Comparable<? super T>>
{
    private int swaps;
    private String[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    }
    public MaxHeap(String[] entries){
        this(entries.length);
        assert initialized = true;
        for(int index = 0; index < entries.length; index++)
            heap[index+1] = entries[index];
        for(int rootIndex = lastIndex/2; rootIndex > 0; rootIndex--){
            reheap(rootIndex);
        }
    }
    public MaxHeap(int initialCapacity){
        if(initialCapacity<DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);
        
        String[] tempHeap = new String[initialCapacity+1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    public boolean isEmpty(){
        return lastIndex < 1;
    }
    public String removeMax(){
        checkInitialization();
        String root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }
    public String getMax(){
        checkInitialization();
        String root = null;
        if(!isEmpty()){
            root = heap[1];
        }
        return root;
    }
    public void reheap(int rootIndex){
        boolean done = false;
        String orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) && (heap[rightChildIndex].compareTo(heap[largerChildIndex])>0)){
                largerChildIndex = rightChildIndex;
            }
            if (orphan.compareTo(heap[largerChildIndex])<0){
                heap[rootIndex] = heap[largerChildIndex];
                swaps++;
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        }
        heap[rootIndex] = orphan;
    }
    public void add(String newEntry){
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex/2;
        while((parentIndex > 0) && (newEntry.compareTo(heap[parentIndex]) > 0)){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }


    private void ensureCapacity(){
        if(lastIndex >= heap.length -1){
            int newLength = 2 * heap.length;
            checkCapacity(newLength);
            heap = Arrays.copyOf(heap, newLength);
        }
    }
    public int getSize(){
        return lastIndex;
    }
    public void clear(){
        checkInitialization();
        while(lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }
    private void checkInitialization(){
        if(!initialized){
            throw new IllegalStateException("Heap not initialized");
        }
    }
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a list whos capacity is above maximum");
        }
    }
    public int optimalMethod(String fileName) throws IOException{
        Scanner fileScan = new Scanner(new File(fileName));
        BufferedWriter outWrite = new BufferedWriter(new FileWriter(new File("outputFile.txt")));
        while(fileScan.hasNextLine()){
            heap[++lastIndex] =  fileScan.nextLine();
            ensureCapacity();
        }
        for(int rootIndex = lastIndex/2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
        for(int i=0; i<10; i++){
            outWrite.write(this.removeMax());
            outWrite.newLine();
        }
        outWrite.write("number of swaps: " + swaps);
        outWrite.newLine();
        outWrite.close();
        fileScan.close();
        return 0;
    }
    public int sequentialInsertions(int array[])
    {
        int numberOfSwaps = 0;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 1; i< array.length+1; i++)
        {
            maxHeap.add(array[i]);
            maxHeap.add(array[2*i]);
            if (heap[i] < heap[2*i])
            {
                maxHeap.removeMax();
                maxHeap.add(array[i]);
                numberOfSwaps++;
            }
            if (heap[i] < heap[(2*i)+1])
            {
                maxHeap.removeMax();
                maxHeap.add(heap[i]);
                numberOfSwaps++;
            }
        }
        return numberOfSwaps;
    }

    
}
