package com.example.baeza.fatherhome.ui.manager;

import com.example.baeza.fatherhome.ui.model.Pastor;

import java.util.List;

public interface PastorListManager {
    interface Model{
        void getPastorListModel(String language);
        void closeDatabaseListenerModel();
    }

    interface View{
        void getPastorListView(List<Pastor> pastorList);
    }

    interface Presenter{
        void getPastorListModel(String language);
        void closeDatabaseListenerModel();

        void getPastorListView(List<Pastor> pastorList);
    }
}
