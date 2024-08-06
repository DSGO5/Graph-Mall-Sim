public class DSAGraph 
{
    private DSALinkedList vertices;
    private DSALinkedList edges;

    public DSAGraph() 
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    public void addVertex(DSAGraphVertex v) //adds vertex to graph
    {
        vertices.insertLast(v);
    }

    public void addEdge(int from, int to, String edgeLabel) //adds edges to a vertex
    {
        DSAGraphVertex vertexFrom = findVertex(from);
        DSAGraphVertex vertexTo = findVertex(to);
        if(vertexFrom != null && vertexTo != null)
        {
            DSAGraphEdge edge = new DSAGraphEdge(vertexFrom, vertexTo, edgeLabel);
            edges.insertLast(edge);
            vertexFrom.addEdge(vertexTo); // Correctly add the edge to the adjacency list
            vertexTo.addEdge(vertexFrom); // Also add the reverse edge
        }
    }

    public void removeVertex(int num) //removes vertex from graph 
    {
        DSAGraphVertex vertexToRemove = findVertex(num);

        if (vertexToRemove != null) 
        {
            // Remove all edges connected to the vertex from the list of edges
            DSALinkedList.DSAListNode edgeNode = edges.getHead();
            while (edgeNode != null) 
            {
                DSAGraphEdge edge = (DSAGraphEdge) edgeNode.getValue();
                if (edge.getFrom() == vertexToRemove || edge.getTo() == vertexToRemove) 
                {
                    edges.remove(edge);
                    edgeNode = edges.getHead(); // Start over because the list has changed
                } 
                else 
                {
                    edgeNode = edgeNode.getNext();
                }
            }

            // Remove the vertex from the list of vertices
            vertices.remove(vertexToRemove);

            // Remove references to the removed vertex from the adjacency lists of other vertices
            DSALinkedList.DSAListNode vertexNode = vertices.getHead();
            while (vertexNode != null) 
            {
                DSAGraphVertex vertex = (DSAGraphVertex) vertexNode.getValue();
                vertex.removeEdge(vertexToRemove);
                vertexNode = vertexNode.getNext();
            }
        }
    }

    public void removeEdge(String edgeLabel) //removes edge from graph
    {
        DSALinkedList.DSAListNode currNode = edges.getHead();
        boolean edgeFound = false;
        while (currNode != null) 
        {
            DSAGraphEdge edge = (DSAGraphEdge) currNode.getValue(); // value = vertex
            if (edge.getLabel().equals(edgeLabel)) 
            {
                // Remove references to the edge from the adjacent vertices
                DSAGraphVertex fromVertex = edge.getFrom();
                DSAGraphVertex toVertex = edge.getTo();
                fromVertex.removeEdge(toVertex);
                toVertex.removeEdge(fromVertex);

                // Remove the edge from the list of edges
                edges.remove(edge);
                currNode = null;
                edgeFound = true;
            }
            else
            {
                currNode = currNode.getNext();
            }
        }
        if (!edgeFound) 
        {
            throw new IllegalArgumentException("Edge not found");
        }

    }

    public boolean hasVertex(int num) //checks if vertex exists in graph
    {
        return findVertex(num) != null;
    }

    private DSAGraphVertex findVertex(int num) //finds vertex in graph
    {
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        DSAGraphVertex foundVertex = null; 
        while(currNode != null)
        {
            DSAGraphVertex vertex = (DSAGraphVertex)currNode.getValue();
            if(vertex.getShopNum() == num)
            {
                foundVertex = vertex;
            }
            currNode = currNode.getNext();
        }
        return foundVertex;
    }

    public DSAGraphVertex getVertex(int num) //wrapper method for findVertex
    {
        return findVertex(num);
    }

    public int getVertexCount() //returns number of vertices in graph
    {
        int count = 0; 
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        while(currNode != null)
        {
            count++;
            currNode = currNode.getNext();
        }
        return count;
    }

    public int getEdgeCount() // returns number of edges in graph
    {
        int count = 0; 
        DSALinkedList.DSAListNode currNode = edges.getHead();
        while(currNode != null)
        {
            count++;
            currNode = currNode.getNext();
        }
        return count;
    }


    public DSAGraphEdge getEdge(String label) //returns edge with label
    {
        DSALinkedList.DSAListNode currNode = edges.getHead();
        while(currNode != null)
        {
            DSAGraphEdge edge = (DSAGraphEdge)currNode.getValue();
            if(edge.getLabel().equals(label))
            {
                return edge;
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    public DSALinkedList getAdjacent(int num)//returns adjacent vertices
    {
        DSAGraphVertex vertex = findVertex(num);
        if(vertex != null)
        {
            return vertex.getAdjacent();
        }
        else
        {
            return null;
        }
    }

    public void displayAsList() //displays the contents of the graph as an adjacency list
    {
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        while(currNode != null)
        {
            DSAGraphVertex vertex = (DSAGraphVertex)currNode.getValue();
            DSALinkedList adjacentVertices = getAdjacent(vertex.getShopNum());
            System.out.print(vertex.getShopName() + ": ");
    
            if (adjacentVertices != null)
            {
                DSALinkedList.DSAListNode adjNode = adjacentVertices.getHead();
                while(adjNode != null)
                {
                    DSAGraphVertex adjVertex = (DSAGraphVertex)adjNode.getValue();
                    System.out.print(adjVertex.getShopName() + " ");
                    adjNode = adjNode.getNext();
                }
            }
            System.out.println();
            currNode = currNode.getNext();
        }
    }
    
    public void displayAsMatrix() //displays the contents of the graph as an adjacency matrix
    {
        int numVertices = getVertexCount();
        String[][] matrix = new String[numVertices + 1][numVertices + 1];
        String[] labels = new String[numVertices];
    
        // Initialize labels array and vertex indices
        DSALinkedList.DSAListNode vertexNode = vertices.getHead();
        int i = 0;
        while (vertexNode != null) 
        {
            DSAGraphVertex vertex = (DSAGraphVertex) vertexNode.getValue();
            labels[i] = vertex.getShopName();
            i++;
            vertexNode = vertexNode.getNext();
        }
    
        // Initialize labels in the first row and first column
        matrix[0][0] = " ";
        for (i = 1; i <= numVertices; i++) 
        {
            matrix[0][i] = labels[i - 1];
            matrix[i][0] = labels[i - 1];
        }
    
        for (int row = 1; row <= numVertices; row++) 
        {
            for (int col = 1; col <= numVertices; col++) 
            {
                matrix[row][col] = "0";
            }
        }
    
        DSALinkedList.DSAListNode edgeNode = edges.getHead();
        while (edgeNode != null) 
        {
            DSAGraphEdge edge = (DSAGraphEdge) edgeNode.getValue();
            DSAGraphVertex fromVertex = edge.getFrom();
            DSAGraphVertex toVertex = edge.getTo();
    
            int fromIndex = getVertexIndex(fromVertex.getShopName(), labels) + 1; // +1 to account for labels
            int toIndex = getVertexIndex(toVertex.getShopName(), labels) + 1;
    
            matrix[fromIndex][toIndex] = "1";
            matrix[toIndex][fromIndex] = "1";
            edgeNode = edgeNode.getNext();
        }
    
        for (i = 0; i <= numVertices; i++) 
        {
            for (int j = 0; j <= numVertices; j++) 
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int getVertexIndex(String label, String[] labels) //returns index of vertex
    {
        int index = -1;
        for (int i = 0; i < labels.length; i++) 
        {
            if (labels[i].equals(label)) 
            {
                index =  i;
            }
        }
        return index;
    }

    private DSAGraphVertex getNextUnvisited(DSAGraphVertex vertex) //gets the next unvisited vertex in alphabetical order
    {
        DSALinkedList adjVert = getAdjacent(vertex.getShopNum());
        DSAGraphVertex v = null;

        for(Object obj : adjVert) 
        {
            DSAGraphVertex w = (DSAGraphVertex) obj;
            if (!w.isVisited()) 
            {
                if (v == null || w.getShopName().compareTo(v.getShopName()) < 0 ) 
                {
                    v = w;
                }
            }
        }

        if(v != null) 
        {
            v.setVisited(true);
        }

        return v;
    }

    public DSAGraphVertex updateVertexInformation(int shopNum, int option, String newValue)//updates vertex information
    {
        DSAGraphVertex vertex = findVertex(shopNum);
        if(option == 1)
        {
            vertex.setShopNum(Integer.parseInt(newValue));
        }
        else if(option == 2)
        {
            vertex.setShopName(newValue);
        }
        else if(option == 3)
        {
            vertex.setCategory(newValue);
        }
        else if(option == 4)
        {
            vertex.setShopLocation(newValue);
        }
        else if(option == 5)
        {
            vertex.setShopRating(Integer.parseInt(newValue));
        }
        System.out.println("updated shop:" + vertex);

        return vertex;
    }

    public boolean isEmpty() //checks if graph is empty
    {
        return vertices.isEmpty();
    }

    public boolean hasShop(int shopNum) //checks if a shop with the imported number already exists
    {
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        boolean found = false;
        while(currNode != null)
        {
            DSAGraphVertex vertex = (DSAGraphVertex)currNode.getValue();
            if(vertex.getShopNum() == shopNum)
            {
                found = true;
            }
            currNode = currNode.getNext();
        }
        return found;
    }

    public boolean shopWithLocation(String location) //checks if a shop with imported location already exists
    {
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        boolean found = false;
        while(currNode != null)
        {
            DSAGraphVertex vertex = (DSAGraphVertex)currNode.getValue();
            if(vertex.getShopLocation().equals(location))
            {
                found = true;
            }
            currNode = currNode.getNext();
        }
        return found;
    }


    public void shortestPath(int start, int end) //finds the shortest path between two vertices
    {
        if(start == end)
        {
            throw  new IllegalArgumentException("You are already at that shop!");
        }
        try
        {
            DSAQueue BFS = BFSPath(start, end);
            DSAQueue DFS = DFSPath(start, end);
            DSALinkedList BFSpath = new DSALinkedList();
            DSALinkedList DFSpath = new DSALinkedList();
            BFSpath = findShortestPath(start, end, BFS);
            DFSpath = findShortestPath(start, end, DFS);

            System.out.println("\nBFS Shortest Path: ");
            printList(BFSpath);

            System.out.println("\nDFS Shortest Path: ");
            printList(DFSpath);

            if(BFSpath.getCount() < DFSpath.getCount())
            {
                System.out.println("\nBFS has shortest path: ");
                printList(BFSpath);
            }
            else if(BFSpath.getCount() > DFSpath.getCount())
            {
                System.out.println("\nDFS has shortest path: ");
                printList(DFSpath);
            }
            else if(BFSpath.getCount() == DFSpath.getCount())
            {
                System.out.println("\nBoth BFS and DFS have the shortest path: ");
                printList(BFSpath);
            }
        }
        catch (Exception e)
        {
            System.out.println("\nNo path exisits between shop " + findVertex(start).getShopName() + " and " + findVertex(end).getShopName());
        }
    }

    private void printList(DSALinkedList inQue) //prints the shortest path as a list of vertices
    {   
        DSALinkedList.DSAListNode currNode = inQue.getHead(); 
        System.out.print("(");
        for(int i = 0; i < inQue.getCount(); i++)
        {
            if(i != inQue.getCount() - 1 && (i % 2 == 0 ))
            {
                if(i == 0)
                {
                    System.out.print(currNode.getValue() + "->" );
                }
                else
                {
                    System.out.print(", " + currNode.getValue() + "->" );
                }
            }
            else
            {
                System.out.print(currNode.getValue());
            }
            currNode = currNode.getNext();
        }
        System.out.print(")\n");

    }

    public DSAQueue BFSPath(int start, int end) //finds the path between two vertices using BFS
    {
        DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        

        // Clear visited status for all vertices
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        while (currNode != null) 
        {
            DSAGraphVertex vertex = (DSAGraphVertex) currNode.getValue();
            vertex.setVisited(false);
            currNode = currNode.getNext();
        }
        boolean found = false;
        DSAGraphVertex v = findVertex(start); 
        DSAGraphVertex endV = findVertex(end);

        v.setVisited(true);
        Q.enqueue(v);
        while (!Q.isEmpty() && !found) 
        {
            v = (DSAGraphVertex) Q.dequeue();
            DSAGraphVertex w = getNextUnvisited(v);
            while(w != null && w != endV) 
            {
                T.enqueue(v.getShopName());
                T.enqueue(w.getShopName());
                w.setVisited(true);
                Q.enqueue(w);
                w = getNextUnvisited(v);
            }
            if(w == endV)
            {
                T.enqueue(v.getShopName());
                T.enqueue(w.getShopName());
                found = true; 
            }
        }
        return T;
    }

    public DSAQueue DFSPath(int start, int end) //finds the path between two vertices using DFS
    {
        DSAQueue T = new DSAQueue(); 
        DSAStack S = new DSAStack(); 
        boolean found = false; 
        DSALinkedList.DSAListNode currNode = vertices.getHead();
        while (currNode != null) 
        {
            DSAGraphVertex vertex = (DSAGraphVertex) currNode.getValue();
            vertex.setVisited(false);
            currNode = currNode.getNext();
        }

        DSAGraphVertex v = findVertex(start); 
        DSAGraphVertex endV = findVertex(end);
        v.setVisited(true);
        S.push(v);

        while(!S.isEmpty() && !found)
        {
            DSAGraphVertex w = getNextUnvisited(v);
            if (w != null && w != endV) 
            {
                T.enqueue(v.getShopName());
                T.enqueue(w.getShopName());
                w.setVisited(true);
                S.push(w); 
                v = w; 
            }
            else if(w == endV)
            {
                T.enqueue(v.getShopName());
                T.enqueue(w.getShopName());
                found = true;
            }
            else
            {
                v = (DSAGraphVertex) S.pop();
            }
        
        }
        return T;
    }

    private DSALinkedList findShortestPath(int A, int Z, DSAQueue search) //finds the shortest path between two vertices
    {
        DSALinkedList path = new DSALinkedList(); 
        DSAStack T = new DSAStack(); 
        DSAGraphVertex initial = findVertex(A);
        DSAGraphVertex End = findVertex(Z);
        String firstPop = null, secondPop = null;
        String start = initial.getShopName();
        String end = End.getShopName();
        boolean pathFound = false;

        while(!search.isEmpty()) //reverse the path
        {
            String name = (String) search.dequeue(); 
            T.push(name);
        }
        
        while(!T.isEmpty() || !pathFound) //find the shortest path
        { 
            firstPop = (String) T.pop();
            secondPop = (String) T.pop();
            if(firstPop.equals(end))
            {
                path.insertLast(firstPop);
                path.insertLast(secondPop);
                if(secondPop.equals(start))
                {
                    pathFound = true;
                }
                else
                {
                    end = secondPop; 
                }
            }
            
        }
    
        
        if(A > Z) //reverse the path if the start vertex is greater than the end vertex
        {
            path.reverse();
        }

        return path; 
    }

    public void displayAllVertexes() //displays all vertices in graph
    {
        DSALinkedList.DSAListNode currNode = vertices.getHead();

        while(currNode != null)
        {
            DSAGraphVertex vertex = (DSAGraphVertex)currNode.getValue();
            System.out.println(vertex);
            currNode = currNode.getNext();
        }
    }

}

    