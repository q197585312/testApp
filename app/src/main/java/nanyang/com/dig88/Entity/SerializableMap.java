package nanyang.com.dig88.Entity;

import java.io.Serializable;
import java.util.Map;

public class SerializableMap<K,V> implements Serializable {
    private Map<K,V> map;
    public Map<K,V> getMap()
    {  
        return map;  
    }  
    public void setMap(Map<K,V> map)
    {  
        this.map=map;  
    }  
}  