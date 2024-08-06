public class DSAQueue 
{
    protected Object[] queue;  
    protected int count;
    private static final int DEFAULT_CAPACITY = 100;

    public DSAQueue()
    {
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
       
    }

    public DSAQueue(int maxCapacity)
    {
        queue = new Object[maxCapacity];
        count = 0;

    }

    public int getCount() //returns the count
    {
        return count;
    }

    public boolean isEmpty() //checks if the queue is empty
    {
        return count == 0;
    }

    public boolean isFull() //checks if the queue is full
    {
        return count == queue.length;
    }

    public void enqueue(Object value) //adds a new entry to the queue
    {
        if (isFull())
        {
            throw new RuntimeException("Queue is full");
        }
        else 
        {
            queue[count] = value;
            count++;
        }
    }

    public Object dequeue() //removes the front entry of the queue
    {
        if (isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }
        else 
        {
            Object frontVal = queue[0];
            for(int i = 0; i < count - 1; i++)
            {
                queue[i] = queue[i + 1];
            }
            queue[count - 1] = null;
            count--;
            return frontVal;
        }
    }

    public Object peek() //returns the front entry of the queue
    {
        if (isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }
        else 
        {
            return queue[0];
        }
    }

    public void displayQueue() //displays the queue
    {
        for (int i = 0; i < getCount(); i++) 
        {
            System.out.print(queue[i] + " ");
        }
    }

}

