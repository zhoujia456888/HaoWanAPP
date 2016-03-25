package cn.zhoujia.haowanapp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import cn.zhoujia.haowanapp.Service.LocationService;
import cn.zhoujia.haowanapp.greendao.DaoMaster;
import cn.zhoujia.haowanapp.greendao.DaoSession;
import cn.zhoujia.haowanapp.greendao.NotepadDao;

// 请在AndroidManifest.xml中application标签下android:name中指定该类
public class MyApplication extends Application {
    public static List<Activity> list = new ArrayList<Activity>();
    public LocationService locationService;
    public Vibrator mVibrator;
    // 数据库
    private static SQLiteDatabase db;
    // 管理者
    private DaoMaster mDaoMaster;
    // 会话
    public static DaoSession mDaoSession;
    
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

        //初始化数据库
        initDatabase();

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    //初始化数据库
    private void initDatabase() {
        // 初始化就这个顺序,记着吧 ^_^
        // 此DevOpenHelper类继承自SQLiteOpenHelper,第一个参数Context,第二个参数数据库名字,第三个参数CursorFactory
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "haowanapp.db", null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return db;
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
