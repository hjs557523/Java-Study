import java.util.HashMap;
import java.util.Map;

public class Genericity {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "你好");
        map.put("how are you", "吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you"));
        
        //泛型擦除后：
        //Map map = new HashMap();
        //map.put("hello", "你好");
        //map.put("how are you", "吃了没？");
        //System.out.println((String) map.get("hello"));
        //System.out.println((String) map.get("how are you?"));
    }

}