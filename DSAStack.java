public class DSAStack 
{
    private Object[] stack;  // Using Object array for flexibility
    private int count;
    private static final int DEFAULT_CAPACITY = 100;

    // Default constructor
    public DSAStack()
    {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // Alternate constructor with custom capacity
    public DSAStack(int maxCapacity)
    {
        stack = new Object[maxCapacity];
        count = 0;
    }

    // Accessor for count
    public int getCount()
    {
        return count;
    }

    // Accessor for checking if the stack is empty
    public boolean isEmpty() 
    {
        return count == 0;
    }

    // Accessor for checking if the stack is full
    public boolean isFull() 
    {
        return count == stack.length;
    }

    // Mutator to push a value onto the stack
    public void push(Object value)
    {
        if (isFull())
        {
            throw new RuntimeException("Stack is full");
        }
        else 
        {
            stack[count] = value;
            count++;
        }
    }

    // Mutator to pop and return the top value from the stack
    public Object pop()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        else 
        {
            Object topVal = top();
            stack[count - 1] = null;  // Clear the reference
            count--;
            return topVal;
        }
    }

    // Accessor to retrieve the top value without removing it
    public Object top()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        else 
        {
            return stack[count - 1];
        }
    }
}
