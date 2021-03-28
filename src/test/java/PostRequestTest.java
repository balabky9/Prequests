import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRequestTest {

    private static Type simpleJsonBeginObject = new TypeToken<Map<String,Object>>(){}.getType();
    private static Gson gson = new Gson();

    public boolean compare(List<Map<String,Object>> l1, List<Map<String, Object>> l2){
        if(!(l1.size()==l2.size())){
            return false;
        }
        for(int i=0; i<l1.size(); i++){
            if (!l1.get(i).keySet().equals(l2.get(i).keySet()) ) {
                return false;
            }
            for(String k: l1.get(i).keySet()){
                if(!l1.get(i).get(k).equals(l2.get(i).get(k))){
                    return false;
                }
            }
        }
        return true;
    }


    @Test
    public void postTest() throws IOException {
        //Data
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("title","foo");
        queryMap.put("body","bar");
        queryMap.put("userid",1);
        data.add(queryMap);
        Map<String,Object> queryMap2 = new HashMap<>();
        queryMap.put("title","foo2");
        queryMap.put("body","bar2");
        queryMap.put("userid",2);
        data.add(queryMap2);

        //Headers
        Map<String,Object> headers = new HashMap<>();
        headers.put("Content-type","application/json; charset=UTF-8");
        Prequests req = new Prequests();

        //Pass Array of Json Data
        List<Map<String,Object>> resp = req.post("https://jsonplaceholder.typicode.com/posts",data,headers);
        System.out.println(resp);

        //Pass single json
        resp = req.post("https://jsonplaceholder.typicode.com/posts",queryMap,headers);
        System.out.println(resp);
    }

}