HOW TO RUN PROGRAM: 
    1. Compile all of the files in the directory using the command: javac *.java
    2. Run the program using the command: java Menu 
    3. Press 1 and add the shop to the center. Keep repeating until all shops are added 
    4. Press 2 and add all the edges/connections between shops. Keep repeating until all shops are added
    5. choose any option from the menu and input the proper values to carry out the function 


FILE SUMMARY:
    - DSAGraph.java: 
        Description: This file contains the graph class which is used to store the graph data structure and all of the methods used to manipulate the graph
                     and in this case the shopping center. 
        Dependencies: DSAGraphVertex.java, DSALinkedList.java, DSAQueue.java, DSAGraphEdge.java, DSAStack.java

    - DSAGraphVertex.java: 
        Description: This file contains the vertex class which is used to store the vertex data structure and all of the methods used to manipulate the vertex
                     and in this case the stores. 
        Dependencies: DSALinkedList.java, DSAGraphEdge.java

    - DSAGraphEdge.java:
        Description: This file contains the edge class which is used to store the edge data structure and all of the methods used to manipulate the edge
                     and in this case the paths between stores. 
        Dependencies: None

    - DSAQueue.java:
        Description: This file contains the queue class which is used to store the queue data structure and all of the methods used to manipulate the queue
                     and in this case the queue of vertices, queue of searrching algorithms, etc. 
        Dependencies: DSALinkedList.java

    - DSAStack.java:
        Description: This file contains the stack class which is used to store the stack data structure and all of the methods used to manipulate the stack
                     and in this case the stack of vertices, stack of searrching algorithm paths, etc. 
        Dependencies: DSALinkedList.java

    - DSALinkedList.java:
        Description: This file contains the linked list class and a DSALinkedList.DSAListNode inner Class which is used to store the linked list data structure and all of the methods used to manipulate the linked list
                     and in this case the linked list of vertices, linked list of edges, etc. 
        Dependencies: None 
    
    - DSAHashTable.java: 
        Description: This file contains the hash table class which is used to store the hash table data structure and all of the methods used to manipulate the hash table
                     and in this case the hash table of shops used for the instant shop searching feature. 
        Dependencies: DSAHashEntry.java 

    - DSAHashEntry.java:
        Description: This file contains the hash entry class which is used to store the hash entry and all of the methods used to manipulate the hash entry
                     and in this case the hash entry of shops used for the instant shop searching feature. The HashEntries store a key and a value.
        Dependencies: None

    - DSAHeap.java
        Description: This file contains the heap class which is used to store the heap data structure and all of the methods used to manipulate the heap
                     and in this case the heap of shops used for sorting the shops by rating in the instant shop searching feature. 
        Dependencies: DSAHeapEntry.java

    - DSAHeapEntry.java
        Description: This file contains the heap entry class which is used to store the heap entry and all of the methods used to manipulate the heap entry
                     and in this case the heap entry of shops used for sorting the shops by rating in the instant shop searching feature. The HeapEntries store a priority and a value.
        Dependencies: None

    - Menu.java:
        Description: This file contains the main for the program and is the user interface for the program. It allows the users to access the menu for the shop finding and navigating system. 
        Dependencies: DSAGraph.java, DSAHashTable.java, DSAHeap.java