
import java.util.Arrays;
public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
    private T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this(DEFAULT_CAPACITY);
    }
    public MaxHeap(T[] entries){
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
        
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity+1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    public boolean isEmpty(){
        return lastIndex < 1;
    }
    public T removeMax(){
        checkInitialization();
        T root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }
    public T getMax(){
        checkInitialization();
        T root = null;
        if(!isEmpty()){
            root = heap[1];
        }
        return root;
    }
    public void reheap(int rootIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) && ((Comparable) heap[rightChildIndex]).compareTo(heap[largerChildIndex])>0){
                largerChildIndex = rightChildIndex;
            }
            if (((Comparable) orphan).compareTo(heap[largerChildIndex])<0){
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        }
        heap[rootIndex] = orphan;
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
    
    public int sequentialInsertions(int array[])
    {
        int numberOfSwaps = 0;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 1; i< array.length+1; i++)
        {
            if (array[i]< array[2*i])
            {
                maxHeap.add(array[2*1]);
                numberOfSwaps++;
            }
            if (array[i] < array[(2*i)+1])
            {
                maxHeap. add(array[(2*i)+1]);
                numberOfSwaps++;
            }
            maxHeap.add(array[i]);
        }
        return numberOfSwaps;
    }

    
}
