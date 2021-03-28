import com.google.api.client.http.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static Map<String, Object> getJson(){
        Map<String,Object> context = new HashMap<>();
        context.put("queryId","MNET_TEST");
        List<String> intervals = new ArrayList<>();
        intervals.add("2020-10-14T00:00:00Z/2020-10-14T01:00:00Z");
        List<String> analysis = new ArrayList<>();
        analysis.add("cardinality");
        Map<String,Object> json = new HashMap<>();
        json.put("queryType","segmentMetadata");
        json.put("dataSource","keyword_snap");
        json.put("merge",true);
        json.put("context",context);
        json.put("analysisTypes",analysis);
        json.put("intervals",intervals);
        return json;

    }
    public static void main(String[] args) throws IOException {
        String url = "http://autoopt-druid.analytics.mn/druid/v2";
        Map<String,Object> params = new HashMap<>();
        params.put("userId","1");
        Prequests requests = new Prequests();
        HttpHeaders httpHeaders = new HttpHeaders();
        //List<Map<String,Object>> response = requests.post(url,getJson());
       // System.out.println(response.get(0));
    }
}
