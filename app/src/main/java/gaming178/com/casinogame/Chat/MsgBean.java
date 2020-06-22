package gaming178.com.casinogame.Chat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MsgBean implements Serializable {
    Map<String,String> map;
    //{"type":"login","client_name":"B012222","room_id":"tb61","client_password":"dealer61"}
    public MsgBean(String type, String client_name, String message) {
        map=new HashMap<>();
        map.put("type",type);
        map.put("client_name",client_name);
        map.put("message",message);
    }
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, String> item = it.next();
            builder.append("\"");
            builder.append(item.getKey());
            builder.append("\":\"");
            builder.append(item.getValue());
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("}");
        builder.append("\n");
        return builder.toString();
    }
}
