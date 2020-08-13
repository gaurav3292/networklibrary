package com.kmgrv.networklib.networkmanager;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.kmgrv.networklib.controller.NetworkController;
import com.kmgrv.networklib.handler.DataHandlerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ApiHandler extends NetworkHandler{

    public static final String HTTP_STATUS = "status";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String VOLLEY_ERROR = "VOLLEY_ERROR";


    private DataHandlerCallback mDataHandler;

    private HashMap<String, Object> map = null;

    public static ApiHandler apiHandler;

    public static ApiHandler getInstance(DataHandlerCallback mDataHandler) {
        if (apiHandler == null) {
            apiHandler = new ApiHandler();
        }
        apiHandler.initHashMap();
        apiHandler.setmDataHandler(mDataHandler);
        return apiHandler;
    }

    public void setmDataHandler(DataHandlerCallback mDataHandler) {
        this.mDataHandler = mDataHandler;
    }

    public void initHashMap(){
        map = new HashMap<>();
    }



    @Override
    public void getPostRequestHandler(String url, JSONObject params,String responseMappingKey) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(HTTP_STATUS);
                            if (status.equalsIgnoreCase(SUCCESS)) {

                                map.put(responseMappingKey, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(ERROR)) {
                                map.put(ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        NetworkController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    @Override
    public void getPostNoResponseHandler(String url, JSONObject params) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        NetworkController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public void getPostNoErrorResponseHandler(String url, JSONObject params, String responseMappingKey) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(HTTP_STATUS);
                            if (status.equalsIgnoreCase(SUCCESS)) {

                                map.put(responseMappingKey, jsonObject);
                                mDataHandler.onSuccess(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        NetworkController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public void getGETRequestResponseHandler(String url, JSONObject params, String responseMappingKey) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            map.put(responseMappingKey, jsonObject);
                            mDataHandler.onSuccess(map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());

                map.put(VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);
            }
        });

        // Adding request to request queue

        jsonObjReq.setShouldCache(false);
        NetworkController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


}
