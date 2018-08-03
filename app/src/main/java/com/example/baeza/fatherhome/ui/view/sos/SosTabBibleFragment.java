package com.example.baeza.fatherhome.ui.view.sos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.helper.SosRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SosTabBibleFragment extends Fragment {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public SosTabBibleFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sos_tab_bible, container, false);
        ButterKnife.bind(this, rootView);

        mRecyclerView.setAdapter(new SosRecyclerAdapter(getContext()));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return rootView;
    }
}
