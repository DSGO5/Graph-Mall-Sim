public class DSAHashEntry 
{
    private String key;
    private Object value;

    public DSAHashEntry(String inKey, Object inValue)
    {
        key = inKey;
        value = inValue;
    }

    public DSAHashEntry()
    {
        key = "";
        value = null;
    }

    public String getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public void setKey(String inKey)
    {
        key = inKey;
    }

    public void setValue(Object inValue)
    {
        value = inValue;
    }

}
