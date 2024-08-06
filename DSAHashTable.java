public class DSAHashTable 
{
    private DSALinkedList [] hashArray;
    private int count;
    private float UP_THRESH = 0.7f; 
    private float DOWN_THRESH = 0.3f;
    private int MAX_STEP = 7;


    public DSAHashTable(int size) //constructor, makes the array the size of the next Prime number 
    {
        int actSize = getNextPrime(size);
        hashArray = new DSALinkedList[actSize];
        for(int i = 0; i <= actSize - 1; i++)
        {
            hashArray[i] = new DSALinkedList();
        }
        count = 0;
    }

    public void put(String inCategory, Object inShop) //adds a shop to the hash table
    {
        int hashIdx = hash(inCategory.getBytes());
        DSAHashEntry entry = new DSAHashEntry(inCategory, inShop);
        int step = stepHash(inCategory);
        if(hashArray[hashIdx].isEmpty()) //if teh hash index is empty, insert the entry
        {
            hashArray[hashIdx].insertLast(entry);
            count++;
        }
        else if(!hashArray[hashIdx].isEmpty())
        {
            DSALinkedList.DSAListNode occupied = hashArray[hashIdx].getHead();
            DSAHashEntry occupiedEntry = (DSAHashEntry) occupied.getValue();
            if(occupiedEntry.getKey().equals(inCategory)) //if the category is the same, insert the entry
            {
                hashArray[hashIdx].insertLast(entry);
                count++;
            }
            else //if the category is different, find the next available index and insert the entry
            {
                hashIdx = (hashIdx + step) % hashArray.length;
                if(hashArray[hashIdx].isEmpty() || ((DSAHashEntry) hashArray[hashIdx].getHead().getValue()).getKey().equals(inCategory)) //if the next index is empty or the category is the same, insert the entry
                {
                    hashArray[hashIdx].insertLast(entry);
                }
                else
                {
                    throw new IllegalArgumentException("\nHashing Error! please input a different category\n");
                }
            }
            
        }

        float loadFactor = getLoadFactor();
        if (loadFactor >= UP_THRESH) //resize if it goes above the upper threshold
        {
            reSize(getNextPrime(hashArray.length * 2));
        } 
    }
    
    public void remove(String categoryKey, String shopName) //removes a shop from the hash table
    {
        int hashIdx = find(categoryKey);
        DSALinkedList.DSAListNode current = hashArray[hashIdx].getHead();
        try
        {
            while (current != null) 
            {
                DSAHashEntry entry = (DSAHashEntry) current.getValue();
                if (entry.getKey().equals(categoryKey) && ((DSAGraphVertex) entry.getValue()).getShopName().equals(shopName)) //if the category and shop name are the same, remove the entry
                {
                    hashArray[hashIdx].remove(entry);
                    count--;
                }
                current = current.getNext();
            }
        }
        catch(Exception e)
        {
            System.out.println("Shop not found for category: " + categoryKey + " and Shop: " + shopName);
        }
        
        float loadFactor = getLoadFactor();
        //resize if it goes below the lower threshold 
        if (loadFactor <= DOWN_THRESH) 
        {
            reSize(getNextPrime(hashArray.length / 2));
        }
    }

    public void sortAndPrint(String inKey) //sorts the shops in a category by rating and prints them
    {
        int hashIdx = find(inKey);
        DSAHeap heap = new DSAHeap(hashArray[hashIdx].getCount());
        DSALinkedList.DSAListNode current = hashArray[hashIdx].getHead();
        while (current != null) 
        {
            DSAHashEntry entry = (DSAHashEntry) current.getValue();
            DSAGraphVertex shopEntry = (DSAGraphVertex) entry.getValue();
            heap.add(shopEntry.getShopRating(), shopEntry);
            current = current.getNext();
        }
        heap.heapSort(heap.getCount());
        System.out.println("-----------------------------------------------------------");
        heap.display();
        System.out.println("-----------------------------------------------------------\n");

    }
    
    public float getLoadFactor() //returns the load factor
    {
        return (float)count / (float)hashArray.length;
    }

    /*******************************************************************************************/

    private int stepHash(String inKey) //returns the step hash
    {
        int hashIdx = hash(inKey.getBytes());
        int hashStep = MAX_STEP - (hashIdx % MAX_STEP);

        return hashStep;
    }

    private int find(String inKey) //finds the index of the category
    {
        int hashIdx = hash(inKey.getBytes());
        int ogIdx = hashIdx;
        int step = stepHash(inKey);

        if(hashArray[hashIdx].isEmpty())
        {
            hashIdx = -1;
        }
        else if(!hashArray[hashIdx].isEmpty())
        {
            DSALinkedList.DSAListNode occupied = hashArray[hashIdx].getHead();
            DSAHashEntry occupiedEntry = (DSAHashEntry) occupied.getValue();
            if(occupiedEntry.getKey().equals(inKey))
            {
                hashIdx = ogIdx;
            }
            else
            {
                hashIdx = (hashIdx + step) % hashArray.length;
                if(hashArray[hashIdx].isEmpty() || ((DSAHashEntry) hashArray[hashIdx].getHead().getValue()).getKey().equals(inKey))
                {
                    hashIdx = ogIdx;
                }
            }   
        }
        return hashIdx; // Return the index where the key was found
    }

    public boolean hasKey(String inKey) //checks if the category exists
    {
        int index = hash(inKey.getBytes());
        DSALinkedList.DSAListNode currentNode = hashArray[index].getHead();
        boolean found = false;
        while (currentNode != null) 
        {
            DSAHashEntry entry = (DSAHashEntry) currentNode.getValue();
            if (entry.getKey().equals(inKey)) 
            {
                found = true;
            }
            currentNode = currentNode.getNext();
        }
        return found;
    }


    private int hash(byte[] inKey) //hashes the category
    {
       int a = 63689;
         int b = 378551;
            int hashVal = 0;

        for(int i = 0; i <= inKey.length - 1; i++)
        {
            hashVal = (hashVal * a) + inKey[i];
            a *= b;
        }
        return Math.abs(hashVal % hashArray.length);
    }

    private int hash(byte[] inKey, int tableSize) //hashes the category with a variable table size
    {
        int a = 63689;
        int b = 378551;
        int hashVal = 0;

        for (int i = 0; i < inKey.length; i++) 
        {
            hashVal = (hashVal * a) + inKey[i];
            a *= b;
        }
        return Math.abs(hashVal % tableSize);
    }

    private int getNextPrime(int startVal) //returns the next prime number
    {
        int primeVal;
        if(startVal % 2 == 0)
        {
            primeVal = startVal  - 1;
        }
        else
        {
            primeVal = startVal;
        }

        boolean isPrime = false;

        do
        {
            primeVal = primeVal + 2;
            int i = 3; 
            isPrime = true;
            float rootVal = (float)Math.sqrt(primeVal);
            do
            {
                if(primeVal % i == 0)
                {
                    isPrime = false;
                }
                else
                {
                    i = i + 2;
                }
            }while((i <= rootVal) && (isPrime));
        }while(!isPrime);
        
        return primeVal;
    }
    
    private void reSize(int newSize) //resizes the hash table
    {
        DSALinkedList[] newHashArray = new DSALinkedList[newSize];
        
        // Initialize the new hash array
        for (int i = 0; i < newSize; i++) 
        {
            newHashArray[i] = new DSALinkedList();
        }
        
        // Rehash existing items into the new hash array
        for (DSALinkedList list : hashArray) 
        {
            DSALinkedList.DSAListNode currentNode = list.getHead();
            while (currentNode != null) 
            {
                DSAHashEntry entry = (DSAHashEntry) currentNode.getValue();
                int hashIdx = hash(entry.getKey().getBytes(), newSize); // rehash the key with the new size
                newHashArray[hashIdx].insertLast(entry.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }
}