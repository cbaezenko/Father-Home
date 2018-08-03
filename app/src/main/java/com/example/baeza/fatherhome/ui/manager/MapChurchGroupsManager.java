package com.example.baeza.fatherhome.ui.manager;

import com.example.baeza.fatherhome.ui.view.churchMap.Church;
import com.example.baeza.fatherhome.ui.view.churchMap.GrowthGroup;

import java.util.List;

public interface MapChurchGroupsManager {

    interface Model{
        void getChurchListModel();
        void getGrowthGroupModel();
    }
    interface View{
        void getChurchListView(List<Church> churchList);
        void getGrowthGroupView(List<GrowthGroup> growthGroupList);
    }
    interface Presenter{
        void getChurchListModel();
        void getGrowthGroupModel();

        void getChurchListView(List<Church> churchList);
        void getGrowthGroupView(List<GrowthGroup> growthGroupList);
    }
}
