import java.util.Iterator;

public class DSALinkedList implements Iterable
{
    class DSAListNode 
    {
        private Object value;
        private DSAListNode next; //next pointer
        private DSAListNode prev;  //previous pointer

        public DSAListNode(Object inValue) 
        {
            value = inValue;
            next = null;
            prev = null;  
        }
        
        // Accessors
        public Object getValue() 
        {
            return value;
        }

        public DSAListNode getNext() 
        {
            return next;
        }

        public DSAListNode getPrev() 
        {
            return prev;
        }

        // Mutators
        public void setNext(DSAListNode newNext) 
        {
            next = newNext;
        }

        public void setPrev(DSAListNode newPrev) 
        {
            prev = newPrev;
        }

        public void setValue(Object inValue) 
        {
            value = inValue;
        }
    }

    private DSAListNode head;
    private DSAListNode tail;  

    public DSALinkedList() 
    {
        head = null;
        tail = null;  
    }

    public void insertFirst(Object newValue) //inserts the first value
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;  
        } 
        else 
        {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
    }

    public void insertLast(Object newValue) //inserts the last value
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) 
        {
            head = newNd;
            tail = newNd;
        } 
        else 
        {
            newNd.setPrev(tail);
            tail.setNext(newNd);  
            tail = newNd;
        }
    }

    public boolean isEmpty() 
    {
        return head == null;
    }

    public Object peekFirst() 
    {
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("List is empty");
        } 
        else 
        {
            return head.getValue();
        }
    }

    public Object peekLast() 
    {
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("List is empty");
        } 
        else 
        {
            return tail.getValue();
        }
    }

    public Object removeFirst() //removes the first value
    {
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("List is empty");
        } 
        else 
        {
            Object nodeValue = head.getValue();
            if (head == tail) 
            {  
                head = null;
                tail = null;
            } 
            else 
            {
                head = head.getNext();
                head.setPrev(null);  
            }
            return nodeValue;
        }
    }

    public Object removeLast() //removes the last value
    {
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("List is empty");
        } 
        else 
        {
            Object nodeValue = tail.getValue();
            if (head == tail) 
            {
                head = null;
                tail = null;
            } 
            else 
            {
                tail = tail.getPrev();
                tail.setNext(null);  
            }
            return nodeValue;
        }
    }
    public DSAListNode getHead() //returns the head
    {
        return head;
    }

    public void displayList() //displays the list
    {
        DSAListNode currentNode = head;
        while (currentNode != null) 
        {
            System.out.print(currentNode.getValue() + " ");
            currentNode = currentNode.getNext();
        }
        System.out.println();  // Add a newline after displaying the list
    }

    
    public void remove(Object targetValue) //removes a target value
    {
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("List is empty");
        } 
        else 
        {
            DSAListNode currentNode = head;
            while (currentNode != null) 
            {
                if (currentNode.getValue().equals(targetValue)) 
                {
                    if (currentNode == head) 
                    {
                        // If the target value is in the head node
                        removeFirst();
                    } 
                    else if (currentNode == tail) 
                    {
                        // If the target value is in the tail node
                        removeLast();
                    } 
                    else 
                    {
                        // If the target value is in a node in the middle
                        DSAListNode prevNode = currentNode.getPrev();
                        DSAListNode nextNode = currentNode.getNext();
                        prevNode.setNext(nextNode);
                        nextNode.setPrev(prevNode);
                    }
                    return; // Found and removed the value, exit the method
                }
                currentNode = currentNode.getNext();
            }
            // If the target value was not found in the list
            throw new IllegalArgumentException("Value not found in the list");
        }
    }

    public void reverse() //reverses the list
    {
        DSAListNode currentNode = head;
        DSAListNode tempNode = null;
    
        while (currentNode != null) 
        {
            // Swap the next and prev pointers of the current node
            tempNode = currentNode.getNext();
            currentNode.setNext(currentNode.getPrev());
            currentNode.setPrev(tempNode);
            
            // Move to the next node in the list
            currentNode = tempNode;
    
            // If currentNode is null, tail and head should be swapped
            if (currentNode == null) 
            {
                tempNode = head;
                head = tail;
                tail = tempNode;
            }
        }
    }

    public int getCount() //returns the count
    {
        int count = 0;
        DSAListNode currentNode = head;
        while (currentNode != null) 
        {
            count++;
            currentNode = currentNode.getNext();
        }
        return count;
    }

    @Override
    public Iterator iterator() 
    {
        return new DSALinkedListIterator(this);
    }
    
    private class DSALinkedListIterator implements Iterator 
    {
        private DSAListNode currNode;

        public DSALinkedListIterator(DSALinkedList list) 
        {
            currNode = list.getHead();
        }

        @Override
        public boolean hasNext() 
        {
            return currNode != null;
        }

        @Override
        public Object next() 
        {
            Object value = currNode.getValue();
            currNode = currNode.getNext();
            return value;
        }
    }
}

