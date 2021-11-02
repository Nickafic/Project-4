
import java.util.Arrays;
import java.util.Scanner;
public final class MaxHeap<T>{
    private T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    }
    public MaxHeap(int initialCapacity){
        if(initialCapacity<DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity+1];
        lastIndex = 0;
        initialized = true;
    }

    public boolean isEmpty(){
        return lastIndex < 1;
    }
    public T getMax(){
        checkInitialization();
        T root = null;
        if(!isEmpty()){
            root = heap[1];
        }
        return root;
    }
    public void add(T newEntry){
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex/2;
        while((parentIndex > 0) && ((Comparable) newEntry).compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        
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
}