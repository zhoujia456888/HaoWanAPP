package cn.zhoujia.haowanapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import org.json.JSONException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhoujia.haowanapp.Adapter.JokeAdapter;
import cn.zhoujia.haowanapp.Bean.JokeBean;
import cn.zhoujia.haowanapp.Bean.JokeBean.ShowapiResBodyEntity.ContentlistEntity;
import cn.zhoujia.haowanapp.R;

/**
 * 笑话界面
 * Created by Zhoujia on 2016/3/4.
 */
public class JokeFragment extends Fragment {

    @Bind(R.id.material_listview)
    XRecyclerView materialListview;
    JokeAdapter jokeAdapter;
    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    String title, Text;
    List<ContentlistEntity> contentlistEntityList;
    int page = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        materialListview.setLayoutManager(layoutManager);

        materialListview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        materialListview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        materialListview.setArrowImageView(R.mipmap.iconfont_downgrey);
        LoadingListener(materialListview);

        getJoke(String.valueOf(page));
        return rootView;
    }

    private void LoadingListener(final XRecyclerView materialListview) {
        materialListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //contentlistEntityList.clear();
                        page++;
                        contentlistEntityList.clear();
                        getJoke(String.valueOf(page));
                        jokeAdapter.notifyDataSetChanged();
                        materialListview.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (page > 1) {
                            contentlistEntityList.clear();
                            page++;
                            getJoke(String.valueOf(page));
                            materialListview.loadMoreComplete();
                            jokeAdapter.notifyDataSetChanged();
                            materialListview.refreshComplete();
                        } else if (page == 1) {
                           // contentlistEntityList.clear();
                            materialListview.noMoreLoading();
                        }
                    }
                }, 1000);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void getJoke(String page) {
        Parameters para = new Parameters();
        para.put("page", page);
        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        // logMsg(responseString);
                        //解析返回的数据
                        try {
                            Log.e("JokeFragment", responseString);
                            AnalyzeData(responseString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void AnalyzeData(String responseString) throws JSONException {
        Gson gson = new Gson();
        JokeBean jokeBean = gson.fromJson(responseString, JokeBean.class);
        int res_code=jokeBean.getShowapi_res_code();
        if(res_code==0){
            JokeBean.ShowapiResBodyEntity showapiResBodyEntity = jokeBean.getShowapi_res_body();
            contentlistEntityList = showapiResBodyEntity.getContentlist();

            jokeAdapter = new JokeAdapter(this.getActivity(), contentlistEntityList);
            materialListview.setAdapter(jokeAdapter);
        }else{
            logMsg(jokeBean.getShowapi_res_error());
        }

    }

    public void logMsg(String str) {
        Toast.makeText(this.getActivity(), str, Toast.LENGTH_SHORT).show();

    }

    public static JokeFragment newInstance(String text) {
        JokeFragment jFragment = new JokeFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        jFragment.setArguments(mBundle);
        return jFragment;
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
