package com.example.baeza.fatherhome.ui.presenter;

import android.content.Context;

import com.example.baeza.fatherhome.ui.manager.MapChurchGroupsManager;
import com.example.baeza.fatherhome.ui.model.MapChurchGroupModel;
import com.example.baeza.fatherhome.ui.view.churchMap.Church;
import com.example.baeza.fatherhome.ui.view.churchMap.GrowthGroup;

import java.util.List;

public class MapChurchGroupsPresenter implements MapChurchGroupsManager.Presenter {

    private MapChurchGroupsManager.View mView;
    private MapChurchGroupModel mModel;

    private Context mContext;

    public MapChurchGroupsPresenter(MapChurchGroupsManager.View view, Context context){
        this.mView = view;
        this.mContext = context;
        mModel = new MapChurchGroupModel(this, mContext);
    }

    @Override
    public void getChurchListModel() {
        if(mView != null){
            mModel.getChurchListModel();
        }
    }

    @Override
    public void getGrowthGroupModel() {
        if(mView != null){
            mModel.getGrowthGroupModel();
        }
    }

    @Override
    public void getChurchListView(List<Church> churchList) {
        if(mView != null){
            mView.getChurchListView(churchList);
        }
    }

    @Override
    public void getGrowthGroupView(List<GrowthGroup> growthGroupList) {
        if(mView != null){
            mView.getGrowthGroupView(growthGroupList);
        }
    }
}
