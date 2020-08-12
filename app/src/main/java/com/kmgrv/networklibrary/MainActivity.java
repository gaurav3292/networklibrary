package com.kmgrv.networklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kmgrv.networklib.handler.DataHandlerCallback;
import com.kmgrv.networklib.networkmanager.ApiHandler;
import com.kmgrv.networklibrary.bean.TermsBean;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DataHandlerCallback,View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                ApiHandler.getInstance(this).getPostRequestHandler("https://www.quickforward.in/driver/getlinks",null,"terms");
                break;
        }

    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

    }

    @Override
    public void onFailure(HashMap<String, Object> map) {

    }
}
