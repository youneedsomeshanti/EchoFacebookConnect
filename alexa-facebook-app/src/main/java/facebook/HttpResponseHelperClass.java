package facebook;

import org.json.JSONObject;

public class HttpResponseHelperClass {

     JSONObject responseJson;
     int responseCode;

    HttpResponseHelperClass(JSONObject jsonObject, int integer) {
        this.responseJson = jsonObject;
        this.responseCode = integer;
    }
}
