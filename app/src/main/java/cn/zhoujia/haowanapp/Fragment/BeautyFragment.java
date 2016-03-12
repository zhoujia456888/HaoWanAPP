package cn.zhoujia.haowanapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.huewu.pla.lib.MultiColumnListView;

import org.json.JSONException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Adapter.BeautyAdapter;
import cn.zhoujia.haowanapp.Bean.BeautyBean;
import cn.zhoujia.haowanapp.R;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import cn.zhoujia.haowanapp.Bean.BeautyBean.NewslistEntity;

/**
 * 美女界面
 * Created by Zhoujia on 2016/3/4.
 * change
 */
public class BeautyFragment extends Fragment {

    @Bind(R.id.list_multiColumn)
    MultiColumnListView listMultiColumn;
    @Bind(R.id.beauty_pulltofresh)
    PtrClassicFrameLayout beautyPulltofresh;
    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    List<NewslistEntity> newslistEntityList;

    int num=10;

    public static BeautyFragment newInstance(String text) {
        BeautyFragment bFragment = new BeautyFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        bFragment.setArguments(mBundle);
        return bFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beauty, container, false);
        ButterKnife.bind(this, rootView);

        beautyPulltofresh.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        beautyPulltofresh.refreshComplete();
                        
                        getbeautyData(String.valueOf(num));
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
        beautyPulltofresh.setLastUpdateTimeRelateObject(this);

        beautyPulltofresh.setResistance(1.7f);
        beautyPulltofresh.setRatioOfHeaderHeightToRefresh(1.2f);
        beautyPulltofresh.setDurationToClose(200);
        beautyPulltofresh.setDurationToCloseHeader(1000);
        beautyPulltofresh.setPullToRefresh(false);
        beautyPulltofresh.setKeepHeaderWhenRefresh(true);
        beautyPulltofresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                beautyPulltofresh.autoRefresh();
            }
        }, 150);

        return rootView;
    }

    private void getbeautyData(String num) {
        Parameters para = new Parameters();
        para.put("num", num);
        ApiStoreSDK.execute("http://apis.baidu.com/txapi/mvtp/meinv",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        // logMsg(responseString);
                        //解析返回的数据
                        AnalyzeData(responseString);

                        Log.e("responseString", responseString);
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
        BeautyBean beautyBean=gson.fromJson(responseString, BeautyBean.class);
         newslistEntityList=beautyBean.getNewslist();

        BeautyAdapter beautyAdapter= new BeautyAdapter(this.getActivity(), newslistEntityList);

        listMultiColumn.setAdapter(beautyAdapter);

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
