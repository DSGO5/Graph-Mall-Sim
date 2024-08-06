public class DSAGraphVertex 
{
    private String shopName;
    private int shopNum; 
    private String Category;
    private int shopRating; 
    private String shopLocation; 
    private boolean visited;
    private DSALinkedList adjacentVertex;

    public DSAGraphVertex(int pNum, String pName, String pCategory, String pLocation, int pRating)
    {
        shopNum = pNum;
        shopName = pName;
        Category = pCategory;
        shopLocation = pLocation;
        shopRating = pRating;
        visited = false;
        adjacentVertex = new DSALinkedList();
    }

    public void setShopName(String pName)
    {
        shopName = pName;
    }

    public void setShopNum(int pNum)
    {
        shopNum = pNum;
    }

    public void setCategory(String pCategory)
    {
        Category = pCategory;
    }

    public void setShopLocation(String pLocation)
    {
        shopLocation = pLocation;
    }

    public void setShopRating(int pRating)
    {
        shopRating = pRating;
    }
    
    public String getShopName()
    {
        return shopName;
    }

    public int getShopNum()
    {
        return shopNum;
    }

    public String getCategory()
    {
        return Category;
    }

    public String getShopLocation()
    {
        return shopLocation;
    }

    public int getShopRating()
    {
        return shopRating;
    }

    public DSALinkedList getAdjacent()
    {
        return adjacentVertex;
    }

    public void addEdge(DSAGraphVertex vertex)
    {
        adjacentVertex.insertLast(vertex);
    }

    public void removeEdge(DSAGraphVertex vertex) 
    {
        DSALinkedList.DSAListNode currNode = adjacentVertex.getHead();
        while (currNode != null) 
        {
            DSAGraphVertex adjVertex = (DSAGraphVertex) currNode.getValue();
            if (adjVertex == vertex) 
            {
                adjacentVertex.remove(adjVertex);
            }
            else
            {
                currNode = currNode.getNext();
            }
            currNode = currNode.getNext();
        }
    }

    public void setVisited(boolean visit)
    {
        visited = visit;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public boolean isVisited()
    {
        return visited;
    }

    @Override
    public String toString()
    {
        return ("Shop[Number: " + shopNum + ", Name: " + shopName + ", Category: " + Category + ", Location: " + shopLocation + ", Rating: " + shopRating + "]");
    }
}
