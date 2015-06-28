package networking;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by misaelperezchamorro on 5/26/15.
 */
public class RequestData {
    private String mActionPath;
    private int mRequestMethod;
    private String mBaseURL;
    private HashMap<String, String> mReqHeaders;
    private JSONObject mBody;

    public void addRequestHeaders(String head, String body) {
        mReqHeaders.put(head, body);
    };

    public RequestData(){
        //mBaseURL = "http://192.168.15.8/api/v1";
        mBaseURL = "http://192.168.10.9:4000/api/v1";
        mReqHeaders = defaultHeaders();
    }

    public String getmActionPath() {
        return mActionPath;
    }

    public void setmActionPath(String mActionPath) {
        this.mActionPath = mActionPath;
    }

    public int getmRequestMethod() {
        return mRequestMethod;
    }

    public void setmRequestMethod(int mRequestMethod) {
        this.mRequestMethod = mRequestMethod;
    }

    public String getmBaseURL() {
        return mBaseURL;
    }

    public void setmBaseURL(String mBaseURL) {
        this.mBaseURL = mBaseURL;
    }

    public HashMap<String, String> defaultHeaders(){
        HashMap<String, String> req_headers = new HashMap<String, String>();
        TimeZone tz = TimeZone.getDefault();
        Date now = new Date();
        int offsetFromUtc = tz.getOffset(now.getTime());
        req_headers.put("time-offset",""+offsetFromUtc);
        req_headers.put("Content-Type", "application/json");
        req_headers.put("rj-api-key", "a1LmE6c!M9PzXv50cKEmeK9jm7Mde2E2i6d2n3i");
        return req_headers;
    }

    public String requestURL(){
        return mBaseURL + mActionPath;
    }

    public JSONObject getmBody() {
        return mBody;
    }

    public void setmBody(JSONObject mBody) {
        this.mBody = mBody;
    }
}
