package cn.zhoujia.haowanapp.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.zhoujia.haowanapp.R;

/**
 * Created by Zhoujia on 2016/3/4.
 */
public class SettingsActivity extends BaseActivity{
    private SettingsActivity activity=SettingsActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //设置toolbar标题为空
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle(getString(R.string.setting));
        setSupportActionBar(toolbar);
        //返回按键
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        //变色状态栏
        BaseActivity.ColourStatusBar(activity);
    }
}
