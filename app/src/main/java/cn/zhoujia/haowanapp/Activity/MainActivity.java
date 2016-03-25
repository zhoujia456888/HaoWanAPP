package cn.zhoujia.haowanapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.hybridsquad.android.library.BitmapUtil;

import java.io.File;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import cn.zhoujia.haowanapp.Fragment.BeautyFragment;
import cn.zhoujia.haowanapp.Fragment.JokeFragment;
import cn.zhoujia.haowanapp.Fragment.MainFragment;
import cn.zhoujia.haowanapp.Fragment.NotepadFragment;
import cn.zhoujia.haowanapp.Fragment.TranslateFragment;
import cn.zhoujia.haowanapp.MyApplication;
import cn.zhoujia.haowanapp.R;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    public static WindowManager windowManager;
    private HelpLiveo mHelpLiveo;
    String imagestr;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.list.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserinfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.list.remove(this);
    }

    @Override
    public void onInt(Bundle savedInstanceState) {

        getUserinfo();

        // 创建抽屉菜单选项ITEM
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.weather_page), R.mipmap.ic_weather);
        //mHelpLiveo.addSubHeader(getString(R.string.classify)); //Item subHeader ITEM分类小标题
        mHelpLiveo.add(getString(R.string.beauty_page), R.mipmap.ic_beauty);
        mHelpLiveo.add(getString(R.string.joke_page), R.mipmap.ic_joke);
        mHelpLiveo.add(getString(R.string.translate_page),R.mipmap.ic_translate);
        mHelpLiveo.add(getString(R.string.notepad_page),R.mipmap.ic_notepad);

                mHelpLiveo.addSeparator(); // Item separator//分割线

        with(this).startingPosition(0).addAllHelpItem(mHelpLiveo.getHelp())
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    private void getUserinfo() {
        // 用户信息
        SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
        this.userName.setText(sp.getString("nickname", "").toString().trim());
        this.userEmail.setText(sp.getString("phone", "").toString().trim());
        imagestr=sp.getString("userphotouri", "1////1").toString().trim();
        File file  = new File(imagestr.split("///")[1]);
        if (!file .exists()) {
            this.userPhoto.setImageResource(R.mipmap.ic_launcher);
        } else {
            this.userPhoto.setImageBitmap(BitmapUtil.decodeUriAsBitmap(this, Uri.parse(imagestr)));
        }
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        windowManager=getWindowManager();
        activity=MainActivity.this;

        switch (position) {
            case 0:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                NavigationLiveo.mToolbar.setTitle(getString(R.string.weather_page));
                break;
            case 1:
                mFragment = BeautyFragment.newInstance(mHelpLiveo.get(position).getName());
                NavigationLiveo.mToolbar.setTitle(getString(R.string.beauty_page));
                break;
            case 2:
                mFragment = JokeFragment.newInstance(mHelpLiveo.get(position).getName());
                NavigationLiveo.mToolbar.setTitle(getString(R.string.joke_page));
                break;
            case 3:
                mFragment = TranslateFragment.newInstance(mHelpLiveo.get(position).getName());
                NavigationLiveo.mToolbar.setTitle(getString(R.string.translate_page));
                break;
            case 4:
                mFragment = NotepadFragment.newInstance(mHelpLiveo.get(position).getName());
                NavigationLiveo.mToolbar.setTitle(getString(R.string.notepad_page));
                break;

            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null) {
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), UserDetailInfo.class));

            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            closeDrawer();
        }
    };

    /**
     * 重写返回键实现按两下退出
     */
    private long MEXITTIME = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - MEXITTIME) > 1000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                MEXITTIME = System.currentTimeMillis();
            } else {
                finish();
                MyApplication.exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
