package facebook;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class HttpHelperClass {

    private final String USER_AGENT = "";

    // HTTP GET request
    HttpResponseHelperClass sendGet(URI uri, String authToken) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        request.setHeader("AUTHORIZATION", "Bearer" + " " + authToken);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + uri);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return new HttpResponseHelperClass(new JSONObject(result.toString()), response.getStatusLine().getStatusCode());

    }

    // HTTP POST request
    HttpResponseHelperClass sendPost(URI uri, JsonObject postJson, String accessToken) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);

        // add header
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(postJson.toString()));
        post.setHeader("AUTHORIZATION", "Bearer " + accessToken);


        org.apache.http.HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + uri);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);

        return new HttpResponseHelperClass(new JSONObject(result.toString()), response.getStatusLine().getStatusCode());
    }

    HttpResponseHelperClass sendPut (URI uri, JSONObject putJson, String accessToken) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(uri);

        // add header
        put.setHeader("Content-Type", "application/json");
        put.setEntity(new StringEntity(putJson.toString()));
        put.setHeader("AUTHORIZATION", "Bearer " + accessToken);


        org.apache.http.HttpResponse response = client.execute(put);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);

        return new HttpResponseHelperClass(new JSONObject(result.toString()), response.getStatusLine().getStatusCode());
    }


    HttpResponseHelperClass sendPostWithFormData ( URI uri, List<NameValuePair> params) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);

        // add header
        post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return new HttpResponseHelperClass(new JSONObject(result.toString()), response.getStatusLine().getStatusCode());

    }

}