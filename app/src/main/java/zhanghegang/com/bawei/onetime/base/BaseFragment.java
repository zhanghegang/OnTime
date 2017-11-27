package zhanghegang.com.bawei.onetime.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * current package:zhanghegang.com.bawei.onetime.base
 * Created by Mr.zhang
 * date: 2017/11/23
 * decription:开发
 */

public abstract class BaseFragment extends Fragment {

    public View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(getViewId(),null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumFragment();
    }

    public abstract int getViewId();
    public abstract void initFragment();
    public abstract void resumFragment();
}
