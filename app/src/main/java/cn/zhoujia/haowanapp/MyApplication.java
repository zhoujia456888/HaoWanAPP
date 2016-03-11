package cn.zhoujia.haowanapp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;
import java.util.List;

import cn.zhoujia.haowanapp.Service.LocationService;

// 请在AndroidManifest.xml中application标签下android:name中指定该类
public class MyApplication extends Application {
    public static List<Activity> list = new ArrayList<Activity>();
    public LocationService locationService;
    public Vibrator mVibrator;
    
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

        //API商店key
        ApiStoreSDK.init(this, "f988f792397f7f268c2c899e88da38a3");
    }

    /**
     * 退出程序
     */
    public static void exitSystem() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
