package com.dsy.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by new on 2016-01-18.
 */
public class TabLayoutFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout mWholeLayout = (FrameLayout) inflater.inflate(R.layout.tablayout_fragment, container, false);
        int res = getArguments().getInt("image_id");
        mWholeLayout.setBackgroundResource(res);
        return mWholeLayout;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}