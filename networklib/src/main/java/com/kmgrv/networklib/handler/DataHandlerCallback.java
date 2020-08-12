package com.kmgrv.networklib.handler;

import java.util.HashMap;

public interface DataHandlerCallback {

    public void onSuccess(HashMap<String, Object> map);

    public void onFailure(HashMap<String, Object> map);


}
