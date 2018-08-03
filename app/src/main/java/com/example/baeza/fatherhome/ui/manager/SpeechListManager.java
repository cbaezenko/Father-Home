package com.example.baeza.fatherhome.ui.manager;

import com.example.baeza.fatherhome.ui.model.Speech;

import java.util.List;

public interface SpeechListManager {
    interface Model{
        void getSpeechListModel(String language);
        void closeDatabaseListenerModel();
    }

    interface View{
        void getSpeechListView(List<Speech> speechList);
    }

    interface Presenter{
        void getSpeechListModel(String language);
        void closeDatabaseListenerModel();

        void getSpeechListView(List<Speech> speechList);
    }
}
