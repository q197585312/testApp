package gaming178.com.mylibrary.allinone.util;


import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 */
public class JSONUtils {/*
    public static void JsonObject2HashMap(JSONObject jo, List<Map<?, ?>> rstList) {
        for (Iterator<String> keys = jo.keys(); keys.hasNext();) {
            try {
                String key1 = keys.next();
                System.out.println("key1---" + key1 + "------" + jo.get(key1)
                        + (jo.get(key1) instanceof JSONObject) + jo.get(key1)
                        + (jo.get(key1) instanceof JSONArray));
                if (jo.get(key1) instanceof JSONObject) {

                    JsonObject2HashMap((JSONObject) jo.get(key1), rstList);
                    continue;
                }
                if (jo.get(key1) instanceof JSONArray) {
                    JsonArray2HashMap((JSONArray) jo.get(key1), rstList);
                    continue;
                }
                System.out.println("key1:" + key1 + "----------jo.get(key1):"
                        + jo.get(key1));
                json2HashMap(key1, jo.get(key1), rstList);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    public static void JsonArray2HashMap(JSONArray joArr,
                                         List<Map<?, ?>> rstList) {
        for (int i = 0; i < joArr.size(); i++) {
            try {
                if (joArr.get(i) instanceof JSONObject) {

                    JsonObject2HashMap((JSONObject) joArr.get(i), rstList);
                    continue;
                }
                if (joArr.get(i) instanceof JSONArray) {

                    JsonArray2HashMap((JSONArray) joArr.get(i), rstList);
                    continue;
                }
                System.out.println("Excepton~~~~~");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }*/

    public static void json2HashMap(String key, Object value,
                                    List<Map<?, ?>> rstList) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);
        rstList.add(map);
    }
    public  static  <T> T getT(Object o,int i){
        try {
            return ((Class<T>) ((ParameterizedType)(o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 通过泛型获取到泛型对应的  List 列表的Type，以便Gson可以解析
     * @param subclass
     * @return
     */
    public static Type getListTypeFromGeneric(Class<?> subclass) {
        // 获取类对应的类型，比如说在RecyclerViewFragment<DuiTangInfo>，则将得到RecyclerViewFragment<DuiTangInfo>
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) superclass;
        // 取得泛型类型对应的类型，即得到 DuiTangInfo 最后对应的类型
        return  $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);
        // 生成被 List 所包含的 Type
//        return $Gson$Types.newParameterizedTypeWithOwner(null, List.class, _type);
    }

    /**
     * 通过已有的 {@link Type} 获取到泛型对应的  List 列表的Type，以便Gson可以解析
     * @param type
     * @return
     */
    public static Type getListTypeFromType(Type type) {
        return $Gson$Types.newParameterizedTypeWithOwner(null, List.class, type);
    }
}
