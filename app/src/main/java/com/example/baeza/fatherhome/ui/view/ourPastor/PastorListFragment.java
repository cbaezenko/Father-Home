package com.example.baeza.fatherhome.ui.view.ourPastor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.helper.PastorListRecyclerViewAdapter;
import com.example.baeza.fatherhome.ui.manager.PastorListManager;
import com.example.baeza.fatherhome.ui.model.Pastor;
import com.example.baeza.fatherhome.ui.presenter.PastorListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastorListFragment extends Fragment implements PastorListManager.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Pastor> mPastorList;
    private PastorListRecyclerViewAdapter mPastorListRecyclerViewAdapter;
    private PastorListPresenter mPresenter;

    public PastorListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pastor_list, container, false);
        ButterKnife.bind(this, view);

        mPresenter = new PastorListPresenter(this, getContext());

        mPastorList = new ArrayList<>();
        initRecyclerView();

        return view;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void getPastorListView(List<Pastor> pastorList) {
        mPastorListRecyclerViewAdapter.incomingPastorlist(pastorList);
    }

    private void initRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mPastorListRecyclerViewAdapter = new PastorListRecyclerViewAdapter(mPastorList , getContext());
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mPastorListRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onResume(){
        super.onResume();
        //this must be replace by the preference option selected by the user.
        mPresenter.getPastorListModel(Constants.RUSSIAN_LANGUAGE);
    }

    @Override
    public void onStop(){
        super.onStop();
        mPresenter.closeDatabaseListenerModel();
    }
}
