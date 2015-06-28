package networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by misaelperezchamorro on 6/28/15.
 */
public class MoveCoinRequest {

    private RequestQueue mRequestQueue;
    private static Context mCtx;
    private static MoveCoinRequest mInstance;
    private RequestData requestData;
    private static RequestObjectListener mRequestObjectListener;


    private MoveCoinRequest(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MoveCoinRequest getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MoveCoinRequest(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void startAsynchronousObjectRequest(RequestData requestData, RequestObjectListener listen){
        mRequestObjectListener = listen;
        Log.d("Request", requestData.requestURL());
        Client request = new Client(requestData.getmRequestMethod(),
                requestData.requestURL(),
                requestData.getmBody(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (mRequestObjectListener != null) {
                    mRequestObjectListener.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Request", volleyError.toString());
                if (mRequestObjectListener != null) {
                    mRequestObjectListener.onFail(volleyError);
                }
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

    public interface RequestObjectListener {
        public void onSuccess(JSONObject response);
        public void onFail(VolleyError volleyError);
    }
}
