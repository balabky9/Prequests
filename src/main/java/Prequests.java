import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prequests {
    private HttpTransport httpTransport;
    private JsonFactory jsonFactory;
    private HttpRequestFactory requestFactory;
    private static Type simpleJsonBeginArray = new TypeToken<List<Map<String,Object>>>(){}.getType();
    private static Type simpleJsonBeginObject = new TypeToken<Map<String,Object>>(){}.getType();
    private static Gson gson = new Gson();

    public Prequests(){
        this.httpTransport = new NetHttpTransport();
        this.jsonFactory = new GsonFactory();
        init();
    }

    public Prequests(HttpTransport customTransport, JsonFactory customJsonFactory){
        this.httpTransport=customTransport;
        this.jsonFactory=customJsonFactory;
        init();
    }

    private void init(){
        this.requestFactory = this.httpTransport.createRequestFactory(
                (HttpRequest request)->{
                    request.setParser(new JsonObjectParser(this.jsonFactory));
                });
    }

    private HttpHeaders setHeaders(Map<String,Object> headerMap){
        HttpHeaders headers = new HttpHeaders();
        for(String key: headerMap.keySet()){
            headers.set(key,headerMap.get(key));
        }
        return headers;
    }

    private int isJsonObjectOrArray(String json)  {
        if(json.charAt(0)=='{') return 0;
        else if(json.charAt(0)=='[') return 1;
        else return -1;
    }

    private List<Map<String,Object>> buildResponse(String json) throws MalformedJsonException {
        List<Map<String,Object>> response = new ArrayList<>();
        if(isJsonObjectOrArray(json)==0){
            response.add(gson.fromJson(json, simpleJsonBeginObject));
        }
        else if(isJsonObjectOrArray(json)==1){
            response = gson.fromJson(json,simpleJsonBeginArray);
        }
        else{
            throw new MalformedJsonException("Json is neither array nor object");
        }
        return response;
    }

    private List<Map<String,Object>> execute(HttpRequest req, HttpHeaders headers, boolean headerFlag) throws IOException {
        if(headerFlag){
            req.setHeaders(headers);
        }
        String resp = req.execute().parseAsString();
        return buildResponse(resp);
    }

    /**
     * Performs a get request.
     * @param url String
     * @param queryParams Map<String,Object>
     * @return Response of get-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> get(String url, Map<String,Object> queryParams) throws IOException {
       return get(url,queryParams,null,false);
    }

    /**
     * Performs a post request.
     * @param url String
     * @param queryParams List<Map<String,Object>>
     * @return Response of post-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> post(String url, List<Map<String,Object>> queryParams) throws IOException {
        return post(url,queryParams,null,false);
    }

    /**
     * Performs a post request.
     * @param url String
     * @param queryParams Map<String,Object>
     * @return Response of post-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> post(String url, Map<String,Object> queryParams) throws IOException {
        return post(url,queryParams,null,false);
    }

    /**
     * Performs a get request with headers.
     * @param url String
     * @param queryParams Map<String,Object>
     * @return Response of get-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> get(String url, Map<String,Object> queryParams, Map<String,Object> headers) throws IOException {
        HttpHeaders httpHeaders = setHeaders(headers);
        return get(url,queryParams,httpHeaders,true);
    }

    /**
     * Performs a post request with headers.
     * @param url String
     * @param queryParams List<Map<String,Object>>
     * @return Response of post-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> post(String url, List<Map<String,Object>> queryParams, Map<String,Object> headers) throws IOException {
        HttpHeaders httpHeaders = setHeaders(headers);
        return post(url,queryParams,httpHeaders,true);
    }

    /**
     * Performs a post request with headers.
     * @param url String
     * @param queryParams Map<String,Object>
     * @return Response of post-request as List<Map<String,Object>>
     * @throws IOException
     */
    public List<Map<String,Object>> post(String url, Map<String,Object> queryParams, Map<String,Object> headers) throws IOException {
        HttpHeaders httpHeaders = setHeaders(headers);
        return post(url,queryParams,httpHeaders,true);
    }


    private List<Map<String, Object>> get(String url, Map<String,Object> queryParams, HttpHeaders headerParams, boolean headerFlag) throws IOException {
        GenericUrl urlObject = new GenericUrl(url);
        urlObject.putAll(queryParams);
        HttpRequest req = this.requestFactory.buildGetRequest(urlObject);
        return execute(req,headerParams,headerFlag);

    }

    private List<Map<String,Object>> post(String url, Object postParams, HttpHeaders headerParams, boolean headerFlag) throws IOException {
        GenericUrl urlObject = new GenericUrl(url);
        HttpContent content = new JsonHttpContent(this.jsonFactory,postParams);
        HttpRequest req = this.requestFactory.buildPostRequest(urlObject,content);
        return execute(req,headerParams,headerFlag);
    }
}
