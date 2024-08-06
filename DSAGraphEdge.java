public class DSAGraphEdge 
{
    private DSAGraphVertex from;
    private DSAGraphVertex to;
    private String label;

    public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex, String inLabel) 
    {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
    }

    public String getLabel()
    {
        return label;
    }

    public DSAGraphVertex getFrom()
    {
        return from;
    }

    public DSAGraphVertex getTo()
    {
        return to;
    }

    public boolean isDirected()
    {
        return true;
    }

    public String toString()
    {
        return "Edge[" + from.getShopName() + " -> " + to.getShopName() + ", Label: " + label + "]";
    }
}
