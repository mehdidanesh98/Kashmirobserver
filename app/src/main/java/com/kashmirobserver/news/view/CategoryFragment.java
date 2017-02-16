package com.kashmirobserver.news.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mehdi.kashmirobserver.R;

import java.util.List;

public class CategoryFragment extends Fragment {
    private List<Model> CatNews;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_layout, null);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView1, new CategoryTabFragment()).commit();

        return rootView;
    }
}
