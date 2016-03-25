package cn.zhoujia.haowanapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import org.hybridsquad.android.library.BitmapUtil;
import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.Activity.selectcity.SelectCity;
import cn.zhoujia.haowanapp.R;

/**
 * 用户信息界面
 * Created by Zhoujia on 2015/12/11.
 */
public class UserDetailInfo extends BaseActivity implements CropHandler {
    @Bind(R.id.personal_icon)
    RoundedImageView personalIcon;
    @Bind(R.id.personal_nickname)
    TextView personalNickname;
    @Bind(R.id.personal_realname)
    TextView personalRealname;
    @Bind(R.id.personal_sex)
    TextView personalSex;
    @Bind(R.id.personal_birth)
    TextView personalBirth;
    @Bind(R.id.personal_residcity)
    TextView personalResidcity;
    @Bind(R.id.scroll)
    ScrollView scroll;
    @Bind(R.id.layout_personal_nickname)
    LinearLayout layoutPersonalNickname;
    @Bind(R.id.layout_personal_realname)
    LinearLayout layoutPersonalRealname;
    @Bind(R.id.layout_personal_sex)
    LinearLayout layoutPersonalSex;
    @Bind(R.id.layout_personal_birth)
    LinearLayout layoutPersonalBirth;
    @Bind(R.id.layout_personal_residcity)
    LinearLayout layoutPersonalResidcity;
    int year;
    int month;
    int day;
    @Bind(R.id.personal_phone)
    TextView personalPhone;
    @Bind(R.id.layout_personal_phone)
    LinearLayout layoutPersonalPhone;
    private int selectcity_requestCode = 0;
    String imagestr;
    CropParams mCropParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdatailinfo);
        ButterKnife.bind(this);
        //设置toolbar标题为空
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle(getString(R.string.userdatailinfo));
        setSupportActionBar(toolbar);
        //返回按键
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailInfo.this.finish();
            }
        });
        //变色状态栏
        BaseActivity.ColourStatusBar(UserDetailInfo.this);
        mCropParams = new CropParams(this);
        init();
    }

    private void init() {
        SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
        personalNickname.setText(sp.getString("nickname", "").toString().trim());
        personalRealname.setText(sp.getString("realname", "").toString().trim());
        personalSex.setText(sp.getString("sex", "").toString().trim());
        personalPhone.setText(sp.getString("phone", "").toString().trim());
        personalBirth.setText(sp.getString("birth", "").toString().trim());
        personalResidcity.setText(sp.getString("cityname", "").toString().trim());
        imagestr = sp.getString("userphotouri", "1////1").toString().trim();
        File file = new File(imagestr.split("///")[1]);
        if (!file.exists()) {
            personalIcon.setImageResource(R.mipmap.ic_launcher);
        } else {
            personalIcon.setImageBitmap(BitmapUtil.decodeUriAsBitmap(this, Uri.parse(imagestr)));
        }
    }

    @OnClick({R.id.personal_icon, R.id.layout_personal_nickname, R.id.layout_personal_realname, R.id.layout_personal_sex, R.id.layout_personal_birth, R.id.layout_personal_residcity, R.id.layout_personal_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_icon:
                new MaterialDialog.Builder(UserDetailInfo.this)
                        .items(R.array.take_phone)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                mCropParams.refreshUri();
                                switch (which) {
                                    case 0:
                                        mCropParams.enable = true;
                                        mCropParams.compress = false;
                                        Intent intent = CropHelper.buildCameraIntent(mCropParams);
                                        startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
                                        break;
                                    case 1:
                                        mCropParams.enable = true;
                                        mCropParams.compress = false;
                                        Intent gaintent = CropHelper.buildGalleryIntent(mCropParams);
                                        startActivityForResult(gaintent, CropHelper.REQUEST_CROP);
                                        break;
                                    case 2:
                                        Intent checkintent = new Intent(UserDetailInfo.this, ImageShower.class);
                                        checkintent.putExtra("imagestr", imagestr);
                                        startActivity(checkintent);
                                        break;
                                }
                            }
                        })
                        .positiveText(R.string.cancel)
                        .show();
                break;
            case R.id.layout_personal_nickname:
                new MaterialDialog.Builder(UserDetailInfo.this)
                        .title(R.string.change_nickname)
                        .content(R.string.edit_nickname)
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .inputRange(2, 8)
                        .positiveText(R.string.ok)
                        .negativeText(R.string.cancel)
                        .input(R.string.edit_nickname_2, 0, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(UserDetailInfo.this, "你输入的昵称是： " + input.toString(), Toast.LENGTH_SHORT).show();
                                String nickname = input.toString();
                                personalNickname.setText(nickname);
                                SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("nickname", nickname);
                                editor.commit();
                            }
                        }).show();
                break;
            case R.id.layout_personal_realname:
                new MaterialDialog.Builder(UserDetailInfo.this)
                        .title(R.string.change_realname)
                        .content(R.string.edit_realname)
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .inputRange(1, 10)
                        .positiveText(R.string.ok)
                        .negativeText(R.string.cancel)
                        .input(R.string.edit_realname_2, 0, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(UserDetailInfo.this, "你输入的姓名是： " + input.toString(), Toast.LENGTH_SHORT).show();
                                String realname = input.toString();
                                personalRealname.setText(realname);
                                SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("realname", realname);
                                editor.commit();
                            }
                        }).show();
                break;
            case R.id.layout_personal_phone:
                new MaterialDialog.Builder(UserDetailInfo.this)
                        .title(R.string.edit_phone)
                        .content(R.string.edit_phone2)
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .inputRange(11, 11)
                        .positiveText(R.string.ok)
                        .negativeText(R.string.cancel)
                        .input(R.string.edit_phone2, 0, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Toast.makeText(UserDetailInfo.this, "你输入的手机号码是： " + input.toString(), Toast.LENGTH_SHORT).show();
                                String phone = input.toString();
                                personalPhone.setText(phone);
                                SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("phone", phone);
                                editor.commit();
                            }
                        }).show();
                break;
            case R.id.layout_personal_sex:
                new MaterialDialog.Builder(UserDetailInfo.this)
                        .items(R.array.user_sex)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                String sex = text.toString();
                                personalSex.setText(sex);
                                SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("sex", sex);
                                editor.commit();
                            }
                        })
                        .positiveText(R.string.cancel)
                        .show();
                break;
            case R.id.layout_personal_birth:
                //初始化Calendar日历对象
                Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
                Date mydate = new Date(); //获取当前日期Date对象
                if (personalBirth.getText().toString().trim().equals("")) {
                    mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                    String dstr = personalBirth.getText().toString().trim();
                    Date date = null;
                    try {
                        date = sdf.parse(dstr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mycalendar.setTime(date);

                }
                year = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
                month = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
                day = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
                DatePickerDialog dpd = new DatePickerDialog(UserDetailInfo.this, Datelistener, year, month, day);
                dpd.show();//显示DatePickerDialog组件
                break;
            case R.id.layout_personal_residcity:
                Intent intent = new Intent(UserDetailInfo.this, SelectCity.class);
                startActivityForResult(intent, selectcity_requestCode);
                break;

        }
    }

    //日期选择器
    private DatePickerDialog.OnDateSetListener Datelistener = new DatePickerDialog.OnDateSetListener() {
        /**params：view：该事件关联的组件
         * params：myyear：当前选择的年
         * params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */
        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
            year = myyear;
            month = monthOfYear;
            day = dayOfMonth;
            //更新日期
            String birth = year + "-" + (month + 1) + "-" + day;
            personalBirth.setText(birth);
            SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("birth", birth);
            editor.commit();

        }
    };

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("UserDetailInfo", "requestCode" + requestCode);
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case 0:
                String cityname = data.getStringExtra("cityname");
                if (!cityname.trim().equals("")) {
                    personalResidcity.setText(cityname);
                    SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("cityname", cityname);
                    editor.commit();
                }
                break;
            default:
                CropHelper.handleResult(this, requestCode, resultCode, data);
                Log.e("UserDetailInfo", "照片选取或拍照");
                break;
        }
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        // Original or Cropped uri
        Log.e("UserDetailInfo", "Crop Uri in path: " + uri.getPath());
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphotouri", uri + "");
        editor.commit();
        if (!mCropParams.compress) {
            personalIcon.setImageBitmap(BitmapUtil.decodeUriAsBitmap(this, uri));
        }

    }

    @Override
    public void onCompressed(Uri uri) {
        personalIcon.setImageBitmap(BitmapUtil.decodeUriAsBitmap(this, uri));
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphotouri", uri + "");
        editor.commit();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, "图片截取失败: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleIntent(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
