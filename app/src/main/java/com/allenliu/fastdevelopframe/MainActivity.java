package com.allenliu.fastdevelopframe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.allenliu.library.BaseActivity;
import com.allenliu.library.eventbus.CommonEvent;
import com.allenliu.library.net.BaseCallBack;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends BaseActivity {

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onActivityReceiveEvent(CommonEvent commonEvent) {
        super.onActivityReceiveEvent(commonEvent);
        if (commonEvent.isSuccessful()) {
            switch (commonEvent.getEventType()) {
                case EventType.TESTHTTP:
                    showSnack("request successful");
                    Intent intent = new Intent(this, WebViewActivity.class);
                    ResponseBody responseBody = (ResponseBody) commonEvent.getData();
                    try {
                        intent.putExtra("data", responseBody.string());
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTest:
                showLoadingProgressBar();
                Call<ResponseBody> call = MyApplication.apiService.testHttp();
                call.enqueue(new BaseCallBack<ResponseBody>(EventType.TESTHTTP));

                break;
        }
    }
}
