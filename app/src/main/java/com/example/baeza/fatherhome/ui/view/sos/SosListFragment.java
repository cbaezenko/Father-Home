package com.example.baeza.fatherhome.ui.view.sos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.helper.SosDetailListAdapter;
import com.example.baeza.fatherhome.ui.manager.SosListManager;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;
import com.example.baeza.fatherhome.ui.presenter.SosListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SosListFragment extends Fragment implements SosListManager.View{

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private List<BibleTextContent> mBibleTextContentList;
    private SosDetailListAdapter mSosDetailListAdapter;
    private SosListPresenter mPresenter;

    private int moodPosition;

    public SosListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sos_content, container, false);
        ButterKnife.bind(this, rootView);

        if(getArguments() != null){
            moodPosition = getArguments().getInt(SosListActivity.MOOD_POSITION_KEY);
        }

//        mPresenter = new SosListPresenter(this);
//        mBibleTextContentList = new ArrayList<>();
//        initRecyclerView();

        return rootView;
    }

    @Override
    public void getSosListView(List<BibleTextContent> bibleTextContentList) {
        mBibleTextContentList = bibleTextContentList;
        mSosDetailListAdapter.refreshRecyclerView(mBibleTextContentList);
    }

    private void initRecyclerView(){
        mSosDetailListAdapter = new SosDetailListAdapter(getContext(), mBibleTextContentList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mSosDetailListAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        mPresenter = new SosListPresenter(this, getContext());
        mBibleTextContentList = new ArrayList<>();
        initRecyclerView();

        mPresenter.getSosListModel(Constants.RUSSIAN_LANGUAGE, moodPosition);
    }

    @Override
    public void onStop(){
        super.onStop();
        mPresenter.closeDatabaseListenerModel();
    }
}
