package com.example.baeza.fatherhome.ui.presenter;

import android.content.Context;

import com.example.baeza.fatherhome.ui.manager.SpeechListManager;
import com.example.baeza.fatherhome.ui.model.Speech;
import com.example.baeza.fatherhome.ui.model.SpeechListModel;

import java.util.List;

public class SpeechListPresenter implements SpeechListManager.Presenter {

    private SpeechListManager.View view;
    private SpeechListManager.Model mModel;

    public SpeechListPresenter(SpeechListManager.View view, Context context){
        this.view = view;
        mModel = new SpeechListModel(this, context);
    }

    @Override
    public void getSpeechListModel(String language) {
        if(view != null){
            mModel.getSpeechListModel(language);
        }
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(view != null){
            mModel.closeDatabaseListenerModel();
        }
    }

    @Override
    public void getSpeechListView(List<Speech> speechList) {
        if(view != null){
            view.getSpeechListView(speechList);
        }
    }
}
