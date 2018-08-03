package com.example.baeza.fatherhome.ui.presenter;

import android.content.Context;

import com.example.baeza.fatherhome.ui.manager.SosListManager;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;
import com.example.baeza.fatherhome.ui.model.SosListModel;

import java.util.List;

public class SosListPresenter implements SosListManager.Presenter {

    SosListManager.View mView;
    SosListManager.Model mModel;

    public SosListPresenter(SosListManager.View view, Context context){
        this.mView = view;
        this.mModel = new SosListModel(this, context);
    }

    @Override
    public void getSosListModel(String language , int moodPosition) {
        if(mView != null){
            mModel.getSosListModel(language, moodPosition);
        }
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(mView != null){
            mModel.closeDatabaseListenerModel();
        }
    }

    @Override
    public void getSosListView(List<BibleTextContent> bibleTextContentList) {
        if(mView != null){
            mView.getSosListView(bibleTextContentList);
        }
    }
}
