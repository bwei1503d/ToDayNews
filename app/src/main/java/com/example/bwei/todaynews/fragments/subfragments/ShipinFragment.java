package com.example.bwei.todaynews.fragments.subfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.base.BaseFragment;

/**
 * Created by muhanxi on 17/4/30.
 */

public class ShipinFragment extends BaseFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shipin_fragment,container,false);

        return  view;

    }
}
