package com.kmgrv.networklib.networkmanager;

import org.json.JSONObject;

public abstract class NetworkHandler {

    public abstract void getPostRequestHandler(String url, JSONObject params,String responseMappingKey);
    public abstract void getPostNoResponseHandler(String url, JSONObject params);
    public abstract void getPostNoErrorResponseHandler(String url, JSONObject params,String responseMappingKey);
    public abstract void getGETRequestResponseHandler(String url, JSONObject params,String responseMappingKey);
}
