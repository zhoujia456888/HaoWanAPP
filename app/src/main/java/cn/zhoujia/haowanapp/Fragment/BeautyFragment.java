package cn.zhoujia.haowanapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhoujia.haowanapp.R;

/**
 * 美女界面
 * Created by Zhoujia on 2016/3/4.
 * change
 */
public class BeautyFragment extends Fragment {

    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    public static BeautyFragment newInstance(String text){
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
        return rootView;
    }
}
