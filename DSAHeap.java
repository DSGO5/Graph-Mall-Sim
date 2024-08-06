public class DSAHeap 
{

    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int maxSize)
    {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }

    public void add(int priority, Object value) //adds a new entry to the heap
    {
        DSAHeapEntry newEntry = new DSAHeapEntry(priority, value);
        heap[count] = newEntry;
        trickleUp(count);
        count++;
    }

    private void trickleUp(int index) //trickles up the heap
    {
        int parentIndex = (index - 1) / 2;
        if(index > 0)
        {
            if(heap[index].getPriority() > heap[parentIndex].getPriority())
            {
                DSAHeapEntry temp = heap[parentIndex];
                heap[parentIndex] = heap[index];
                heap[index] = temp;
                trickleUp(parentIndex);
            }
        }
       
    }

    public DSAHeapEntry remove() //removes the root of the heap
    {
        DSAHeapEntry removed = heap[0];
        heap[0] = heap[count - 1];
        heap[count - 1] = null;
        count--;
        trickleDown(0, count);
        return removed;
    }

    private void trickleDown(int index, int count) //trickles down the heap
    {
        int lChildIdx = (index * 2) + 1;
        int rChildIdx = lChildIdx + 1;

        if(lChildIdx < count)
        {
            int largeIdx = lChildIdx;
            if(rChildIdx < count)
            {
                if(heap[lChildIdx].getPriority() > heap[rChildIdx].getPriority())
                {
                    largeIdx = rChildIdx;
                }
            }
            if(heap[largeIdx].getPriority() < heap[index].getPriority())
            {
                DSAHeapEntry temp = heap[index];
                heap[index] = heap[largeIdx];
                heap[largeIdx] = temp;
                trickleDown(largeIdx, count);
            }
        }
    }


    public void display() //displays the heap
    {
        for(int i = 0; i < count; i++)
        {
            System.out.println(heap[i].getValue());
        }
    }

    public void heapify(int count) //heapifies the heap
    {
        for(int i = (count / 2) - 1; i >= 0; i--)
        {
            trickleDown(i, count);
        }
    }

    public void heapSort(int count) //sorts the heap in descending order
    {
        heapify(count);
        for (int i = count - 1; i > 0; i--)
        {
            DSAHeapEntry temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            trickleDown(0, i);
        }
    }

    public DSAHeapEntry[] getHeap() //returns the heap
    {
        return heap;
    }

    public int getCount() //returns the count
    {
        return count;
    }
}