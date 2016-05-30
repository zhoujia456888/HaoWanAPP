package cn.zhoujia.haowanapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhoujia.haowanapp.Bean.ShenZTBean;
import cn.zhoujia.haowanapp.R;

/**
 * Created by Zhoujia on 2016/5/25.
 */
public class ShenZTFragment extends Fragment {
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    @Bind(R.id.edittxt_shenztcardno)
    EditText edittxtShenztcardno;
    @Bind(R.id.btn_searchshenztcar)
    Button btnSearchshenztcar;
    @Bind(R.id.txt_shenztbalance)
    TextView txtShenztbalance;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static ShenZTFragment newInstance(String name) {
        ShenZTFragment sFragment = new ShenZTFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, name);
        sFragment.setArguments(mBundle);
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shenzt, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_searchshenztcar)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_searchshenztcar:
                String shenztCardno = edittxtShenztcardno.getText().toString().trim();
                gettranslateData(shenztCardno);
                break;
        }
    }

    private void gettranslateData(String shenztCardno) {
        Parameters para = new Parameters();
        para.put("id", shenztCardno);
        para.put("format", "json");
        ApiStoreSDK.execute("http://apis.baidu.com/appangel/shenzhentong/shenzhentong", ApiStoreSDK.GET, para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        // logMsg(responseString);
                        //解析返回的数据
                        KLog.json("responseString", responseString);
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
        ShenZTBean shenztBean = gson.fromJson(responseString, ShenZTBean.class);
        String shenztbalance = shenztBean.getData().getCard_balance();
        txtShenztbalance.setText(shenztbalance);

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
