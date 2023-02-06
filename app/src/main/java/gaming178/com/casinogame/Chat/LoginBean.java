package gaming178.com.casinogame.Chat;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/29.
 */
public class LoginBean implements Serializable {
    /*      obj.type = "login";  // type 定义类型为 "login"
            obj.client_name = userName;   // client_name 是用户名
            obj.room_id =  room_id ;  // room_id 房间号
     obj.client_password = userPass ; // client_password 密码*/

    Map<String,String> map;
    //{"type":"login","client_name":"B012222","room_id":"tb61","client_password":"dealer61"}
    public LoginBean(String type, String client_name, String room_id, String client_password) {
        map=new HashMap<>();
        map.put("type",type);
        map.put("client_name",client_name);
        map.put("room_id",room_id);
        map.put("client_password",client_password);
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
