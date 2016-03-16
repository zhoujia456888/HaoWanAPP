package cn.zhoujia.haowanapp.Fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.AutoText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.Bean.TranslateBean;
import cn.zhoujia.haowanapp.R;
import cn.zhoujia.haowanapp.Bean.TranslateBean.RetDataEntity.TransResultEntity;

/**
 * Created by Zhoujia on 2016/3/15.
 */
public class TranslateFragment extends Fragment {
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    @Bind(R.id.spinner_from_lang)
    NiceSpinner spinnerFromLang;
    @Bind(R.id.spinner_to_lang)
    NiceSpinner spinnerToLang;
    @Bind(R.id.edittxt_translate_from)
    EditText edittxtTranslateFrom;
    @Bind(R.id.btn_translate)
    Button btnTranslate;
    @Bind(R.id.txt_translate_result)
    TextView txtTranslateResult;
    List<String> fromLang = new LinkedList<>(Arrays.asList("中文-zh", "英语-en", "自动检测-auto", "日语-jp", "韩语-kor", "西班牙语-spa", "法语-fra", "泰语-th", "阿拉伯语-ara", "俄罗斯语-ru", "葡萄牙语-pt", "文言文-wyw"
            , "德语-de", "意大利语-it", "荷兰语-nl", "希腊语-el"));
    List<String> toLang = new LinkedList<>(Arrays.asList("英语-en", "中文-zh", "自动检测-auto", "日语-jp", "韩语-kor", "西班牙语-spa", "法语-fra", "泰语-th", "阿拉伯语-ara", "俄罗斯语-ru", "葡萄牙语-pt", "文言文-wyw"
            , "德语-de", "意大利语-it", "荷兰语-nl", "希腊语-el"));

    List<TransResultEntity> transResultEntityList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ButterKnife.bind(this, rootView);
        spinnerFromLang.attachDataSource(fromLang);
        spinnerToLang.attachDataSource(toLang);
        String str = fromLang.get(spinnerFromLang.getSelectedIndex()).toString();
        Log.e("TranslateFragment", str);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static Fragment newInstance(String name) {
        TranslateFragment tFragment = new TranslateFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, name);
        tFragment.setArguments(mBundle);
        return tFragment;
    }

    @OnClick({R.id.spinner_from_lang, R.id.spinner_to_lang, R.id.btn_translate, R.id.txt_translate_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spinner_from_lang:
                break;
            case R.id.spinner_to_lang:
                break;
            case R.id.btn_translate:
                String fromlang = spinnerFromLang.getText().toString().trim().split("-")[1];
                String tolang = spinnerToLang.getText().toString().trim().split("-")[1];
                String translatedata = edittxtTranslateFrom.getText().toString();
                Log.d("TranslateFragment", fromlang);
                Log.d("TranslateFragment", tolang);
                gettranslateData(fromlang, tolang, translatedata);
                break;
            case R.id.txt_translate_result:
                break;
        }
    }

    private void gettranslateData(String from, String to, String query) {
        Parameters para = new Parameters();
        para.put("query", query);
        para.put("from", from);
        para.put("to", to);
        ApiStoreSDK.execute("http://apis.baidu.com/apistore/tranlateservice/translate", ApiStoreSDK.GET, para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        // logMsg(responseString);
                        //解析返回的数据
                        Log.e("responseString", responseString);
                        AnalyzeData(responseString);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                        logMsg(getStackTrace(e));
                    }
                });
    }

    private void AnalyzeData(String responseString) {
        Gson gson = new Gson();
        TranslateBean translateBean = gson.fromJson(responseString, TranslateBean.class);
        transResultEntityList = translateBean.getRetData().getTrans_result();

        String dst = transResultEntityList.get(0).getDst();
        ToText(dst);

    }

    private void ToText(String dst) {
        txtTranslateResult.setText(dst.toString());
        txtTranslateResult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .items(R.array.share_joke)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Intent shareIntent = new Intent();
                                        shareIntent.setAction(Intent.ACTION_SEND);
                                        shareIntent.putExtra(Intent.EXTRA_TEXT, txtTranslateResult.getText().toString());
                                        shareIntent.setType("text/plain");
                                        //设置分享列表的标题，并且每次都显示分享列表
                                        getActivity().startActivity(Intent.createChooser(shareIntent, "分享到"));
                                        break;
                                    case 1:
                                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                        // 将文本内容放到系统剪贴板里。
                                        cm.setText(txtTranslateResult.getText().toString());
                                        Toast.makeText(getActivity(), "内容已经复制到剪切板", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        })
                        .positiveText(R.string.cancel)
                        .show();
                return false;
            }
        });

    }

    public void logMsg(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    String getStackTrace(Throwable e) {
        if (e == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        str.append(e.getMessage()).append("\n");
        for (int i = 0; i < e.getStackTrace().length; i++) {
            str.append(e.getStackTrace()[i]).append("\n");
        }
        return str.toString();
    }
}
