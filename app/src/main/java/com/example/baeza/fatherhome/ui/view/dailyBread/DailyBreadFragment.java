package com.example.baeza.fatherhome.ui.view.dailyBread;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.helper.DailyBreadRecyclerAdapter;
import com.example.baeza.fatherhome.ui.manager.DailyBreadListManager;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;
import com.example.baeza.fatherhome.ui.presenter.DailyBreadListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyBreadFragment extends Fragment implements DailyBreadListManager.View {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private DailyBreadListPresenter mPresenter;
    private List<BibleTextContent> mBibleTextContentList;
    private DailyBreadRecyclerAdapter mDailyBreadRecyclerAdapter;

    private static final String SAVE_INSTANCE_KEY = "saveInstanceKey";

    public DailyBreadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_day_bread, container, false);
        ButterKnife.bind(this, rootView);

        if(savedInstanceState == null) {
            mPresenter = new DailyBreadListPresenter(this, getContext());
            mBibleTextContentList = new ArrayList<>();
            mPresenter.getDailyListModel(Constants.RUSSIAN_LANGUAGE);
            initRecyclerView();
        }else {
            mPresenter = new DailyBreadListPresenter(this, getContext());
            mBibleTextContentList = savedInstanceState.getParcelableArrayList(SAVE_INSTANCE_KEY);
            mPresenter.getDailyListModel(Constants.RUSSIAN_LANGUAGE);
            initRecyclerView();
        }

        return rootView;
    }

    private void initRecyclerView(){
        mDailyBreadRecyclerAdapter = new DailyBreadRecyclerAdapter(getContext(), mBibleTextContentList);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mDailyBreadRecyclerAdapter);
    }

    @Override
    public void getDailyListView(List<BibleTextContent> bibleTextContentList) {
        mBibleTextContentList = bibleTextContentList;
        mDailyBreadRecyclerAdapter.refreshRecyclerView(mBibleTextContentList);
    }

    @Override
    public void onResume(){
        super.onResume();
//
//        mPresenter = new DailyBreadListPresenter(this);
//        mBibleTextContentList = new ArrayList<>();
//        mPresenter.getDailyListModel(Constants.RUSSIAN_LANGUAGE);
//        initRecyclerView();
    }

    @Override
    public void onStop(){
        super.onStop();
        mPresenter.closeDatabaseListenerModel();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelableArrayList(SAVE_INSTANCE_KEY, (ArrayList<? extends Parcelable>) mBibleTextContentList);
        super.onSaveInstanceState(savedInstanceState);
    }
}
