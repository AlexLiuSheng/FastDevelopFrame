# FastDevelopFrame
 a fast develop frame on android base on [EventBus](https://github.com/greenrobot/EventBus) .[Retrofit](https://github.com/square/retrofit) .[OkHttp](https://github.com/square/okhttp).And having some useful utils.  

- [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode) 
## include

 `compile 'com.allenliu:fastdevelopframe:1.0.2'` 

## use  
- Activity extends `BaseActivity` and Fragment extends `BaseFragment` 
- app Application extends `BaseApplication` - Activity or Fragment override method `onActivityReceiveEvent` or `onFragmentReceiveEvent`   by receiving your event ,then just deal with the event.
 - http request just use your api **call** `enqueue` and deliver a `BaseCallBack`.  

eg.

     ``` 
    Call<ResponseBody> call = MyApplication.apiService.testHttp(); 
    call.enqueue(new BaseCallBack<ResponseBody>(EventType.TESTHTTP));
     ```  


