package com.example.baeza.fatherhome.ui.presenter;

import android.content.Context;

import com.example.baeza.fatherhome.ui.manager.PastorListManager;
import com.example.baeza.fatherhome.ui.model.Pastor;
import com.example.baeza.fatherhome.ui.model.PastorListModel;

import java.util.List;

public class PastorListPresenter implements PastorListManager.Presenter {

    private PastorListManager.View view;
    private PastorListManager.Model mModel;

    public PastorListPresenter(PastorListManager.View view, Context context){
        this.view = view;
        mModel = new PastorListModel(this, context);
    }

    @Override
    public void getPastorListModel(String language) {
        if(view != null){
            mModel.getPastorListModel(language);
        }
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(view != null){
            mModel.closeDatabaseListenerModel();
        }
    }

    @Override
    public void getPastorListView(List<Pastor> pastorList) {
        if(view != null){
            view.getPastorListView(pastorList);
        }
    }
}
