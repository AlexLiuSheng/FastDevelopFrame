package com.allenliu.library.net;

import com.allenliu.library.eventbus.CommonEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by allenliu on 2017/5/26.
 */

public class BaseCallBack<T> implements Callback<T> {
    private int eventType;
    public BaseCallBack(int eventType) {
        this.eventType=eventType;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        CommonEvent commonEvent = new CommonEvent();
        commonEvent.setEventType(eventType);
        commonEvent.setResponseCode(response.code());
        if (response.isSuccessful()) {
            commonEvent.setSuccessful(true);
            commonEvent.setMessage("OK");
            commonEvent.setData(response.body());

        } else {
            commonEvent.setSuccessful(false);
            if (response.code() == 404) {
                commonEvent.setMessage("Not found");
            } else {
                if (response.errorBody() != null) {
                    try {
                        commonEvent.setMessage(response.errorBody().string());
                    } catch (Exception e) {
                        commonEvent.setMessage("Internal Server Error");
                    }
                } else {
                    commonEvent.setMessage("Unknown Error");
                }
            }


            commonEvent.setData(null);
        }
        EventBus.getDefault().post(commonEvent);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
       // LogUtils.e(t.getMessage());
        CommonEvent commonEvent = new CommonEvent();
        commonEvent.setEventType(eventType);
        commonEvent.setSuccessful(false);
        //commonEvent.setMessage();
        commonEvent.setMessage("Network Error.Please Try Again");
        EventBus.getDefault().post(commonEvent);
    }
}
