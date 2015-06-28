package networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by misaelperezchamorro on 6/28/15.
 */
public class Client extends JsonObjectRequest {
    private Response.Listener<JSONObject> listener;
    private Map<String, String> params;

    public Client(int method, String url, JSONObject body, Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener){
        super(method, url,body,reponseListener,errorListener);
        this.listener = reponseListener;
        this.params = params;

    }
    @Override
    protected void deliverResponse(JSONObject jsonObject) {
        listener.onResponse(jsonObject);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("rj-api-key", "a1LmE6c!M9PzXv50cKEmeK9jm7Mde2E2i6d2n3i");
        return headers;
    }

    public static boolean hasNetworkConnection(Context ctx) {
        boolean connection = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] networks = connectivityManager.getAllNetworkInfo();

        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (networks[i].getState() == NetworkInfo.State.CONNECTED) {
                connection = true;
            }
        }
        return connection;
    }
}
