package com.lewis.codingrode.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.codingrode.R;

/**
 * Created by lewis on 2017/4/19.
 */

public class ContractFragment extends Fragment{

    public static ContractFragment newInstance() {
        ContractFragment fragment = new ContractFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contract, container, false);
    }
}
