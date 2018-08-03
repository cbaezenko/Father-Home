package com.example.baeza.fatherhome.ui.view.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.helper.AboutRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDetailFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private String[] descriptionArray;

    public AboutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null){
            descriptionArray = getArguments().getStringArray(AboutDetailActivity.ABOUT_DESCRIPTION_KEY);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter( new AboutRecyclerAdapter(descriptionArray));
        }
        return  view;
    }
}
