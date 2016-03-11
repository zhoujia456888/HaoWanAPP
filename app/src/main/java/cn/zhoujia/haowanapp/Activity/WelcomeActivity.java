package cn.zhoujia.haowanapp.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.MyApplication;
import cn.zhoujia.haowanapp.R;
import cn.zhoujia.haowanapp.Utils.NetUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Zhoujia on 2016/3/4.
 */
public class WelcomeActivity extends Activity {
    @Bind(R.id.shimmer_appname)
    ShimmerTextView shimmerAppname;
    @Bind(R.id.hint_welcome)
    TextView hintWelcome;
    Shimmer shimmer;
    private final int SDK_PERMISSION_REQUEST = 127;
    private String permissionInfo;

    Activity activity = WelcomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        getPersimmions();

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/xingkai.TTF");
        //使用字体
        shimmerAppname.setTypeface(typeFace);
        shimmer = new Shimmer();
        shimmer.start(shimmerAppname);

        //检测是否链接上网络
        if (checkNetType()) {
            startntent(2000);
        } else {
            new MaterialDialog.Builder(activity)
                    .title(getString(R.string.neterror))
                    .content(getString(R.string.neterrorinfo))
                    .positiveText(R.string.ok)
                    .onAny(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            switch (which) {
                                case POSITIVE:
                                    startntent(1000);
                                    break;
                            }
                        }
                    })
                    .show();

        }
    }

    private void startntent(int sleeptime) {
        //两秒跳转到主界面
        final Intent mainIntent = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(mainIntent);
                WelcomeActivity.this.finish();
            }
        };
        timer.schedule(tast, sleeptime);
    }

    @OnClick({R.id.shimmer_appname, R.id.hint_welcome})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shimmer_appname:
                break;
            case R.id.hint_welcome:
                break;
        }
    }

    /**
     * 检查网络是否可用，判断是用手机网络还是WIFI网络或者模块热点.
     */
    private Boolean checkNetType() {
        int type = NetUtils.getConnectedType(this);
        if (type != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        shimmer.cancel();
        MyApplication.list.remove(this);
        super.onDestroy();
    }

    //定位权限申请
    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
