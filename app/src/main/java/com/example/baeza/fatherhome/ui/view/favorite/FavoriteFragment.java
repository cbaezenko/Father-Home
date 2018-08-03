package com.example.baeza.fatherhome.ui.view.favorite;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.database.AppDatabase;
import com.example.baeza.fatherhome.database.FavoriteEntry;
import com.example.baeza.fatherhome.ui.helper.FavoriteRecyclerAdapter;
import com.example.baeza.fatherhome.ui.helper.MyViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private AppDatabase mAppDatabase;
    private Context mContext;

    private FavoriteRecyclerAdapter mFavoriteRecyclerAdapter;
    private LiveData<List<FavoriteEntry>> mFavoriteEntryList;

    public FavoriteFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);

        mContext = container.getContext();
//        mAppDatabase = AppDatabase.getInstance(container.getContext());
//
//        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<FavoriteEntry>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteEntry> favoriteEntryList) {
                mFavoriteRecyclerAdapter = new FavoriteRecyclerAdapter(favoriteEntryList);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(mFavoriteRecyclerAdapter);
            }
        });
//        mFavoriteEntryList.observe(this, () );
//        mFavoriteEntryList = mAppDatabase.mFavoriteDao().loadAllFavorite();
        

//        mFavoriteRecyclerAdapter = new FavoriteRecyclerAdapter(mFavoriteEntryList);
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setAdapter(mFavoriteRecyclerAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        mAppDatabase = AppDatabase.getInstance(mContext);
        initRecyclerView();
    }
}
