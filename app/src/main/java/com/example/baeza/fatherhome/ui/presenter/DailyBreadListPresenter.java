package com.example.baeza.fatherhome.ui.presenter;

import android.content.Context;

import com.example.baeza.fatherhome.ui.manager.DailyBreadListManager;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;
import com.example.baeza.fatherhome.ui.model.DailyBreadListModel;

import java.util.List;

public class DailyBreadListPresenter implements DailyBreadListManager.Presenter {

    private DailyBreadListManager.View mView;
    private DailyBreadListManager.Model mModel;

    public DailyBreadListPresenter(DailyBreadListManager.View view, Context context){
        this.mView = view;
        mModel = new DailyBreadListModel(this, context);
    }

    @Override
    public void getDailyListModel(String language) {
        if(mView != null){
            mModel.getDailyListModel(language);
        }
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(mView != null){
            mModel.closeDatabaseListenerModel();
        }
    }

    @Override
    public void getDailyListView(List<BibleTextContent> bibleTextContentList) {
        if(mView != null){
            mView.getDailyListView(bibleTextContentList);
        }
    }
}
