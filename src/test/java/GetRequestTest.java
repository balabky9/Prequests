import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequestTest {

    private static Type simpleJsonBeginArray = new TypeToken<List<Map<String,Object>>>(){}.getType();
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
    public void getTest() throws IOException {
        String respJson = "{\n" +
                "\t\"userId\": 1,\n" +
                "\t\"id\": 1,\n" +
                "\t\"title\": \"delectus aut autem\",\n" +
                "\t\"completed\": false\n" +
                "}";
        List<Map<String,Object>> response = new ArrayList<>();
        response.add(gson.fromJson(respJson,simpleJsonBeginObject));
        HashMap<String,Object> queryMap = new HashMap<>();
        Prequests req = new Prequests();
        List<Map<String,Object>> queryResp = req.get("https://jsonplaceholder.typicode.com/todos/1",queryMap);
        System.out.println(compare(response,queryResp));
    }

    @Test
    public void getTestListOfJson() throws IOException {
        List<Map<String,Object>> response;
        Prequests req = new Prequests();
        //Pass an empty queryMap if no query Params are needed.
        HashMap<String,Object> queryMap = new HashMap<>();
        response = req.get("https://jsonplaceholder.typicode.com/users",queryMap);
        System.out.println(response);
    }

}
