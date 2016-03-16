package cn.zhoujia.haowanapp.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Adapter.BeautyAdapter;
import cn.zhoujia.haowanapp.Bean.BeautyBean;
import cn.zhoujia.haowanapp.Bean.BeautyBean.TngouEntity;
import cn.zhoujia.haowanapp.R;

/**
 * 美女界面
 * Created by Zhoujia on 2016/3/4.
 * change
 */
public class BeautyFragment extends Fragment {

    @Bind(R.id.beauty_xrecyclerview)
    XRecyclerView beautyXrecyclerview;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    List<TngouEntity> tngouEntityList;
    BeautyAdapter beautyAdapter;
    int num = 20;
    long id=0;
    int classify=1;


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
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        beautyXrecyclerview.setLayoutManager(layoutManager);

        beautyXrecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        beautyXrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        beautyXrecyclerview.setArrowImageView(R.mipmap.iconfont_downgrey);
        LoadingListener(beautyXrecyclerview);

        getbeautyData(num);
        return rootView;
    }

    private void LoadingListener(final XRecyclerView beautyXrecyclerview) {
        beautyXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getbeautyData(num);
                        beautyAdapter.notifyDataSetChanged();
                        beautyXrecyclerview.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getbeautyData(num);
                        beautyXrecyclerview.loadMoreComplete();
                        beautyAdapter.notifyDataSetChanged();
                        beautyXrecyclerview.refreshComplete();
                    }
                }, 1000);
            }
        });
        beautyXrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void getbeautyData(int  num) {
        id=(long)(1+Math.random()*(10-1+1));
        classify=(int)(1+Math.random()*(10-1+1));
        Parameters para = new Parameters();
//        para.put("id", 1);
//        para.put("rows", num);
//        para.put("classify", 1);
        ApiStoreSDK.execute("http://www.tngou.net/tnfs/api/news?"+"id="+id+"&rows="+num+"&classify="+classify, ApiStoreSDK.GET, para,
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
        BeautyBean beautyBean = gson.fromJson(responseString, BeautyBean.class);
        tngouEntityList = beautyBean.getTngou();
        beautyAdapter = new BeautyAdapter(this.getActivity(), tngouEntityList);
        beautyXrecyclerview.setAdapter(beautyAdapter);

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
