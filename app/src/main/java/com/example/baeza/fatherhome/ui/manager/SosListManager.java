package com.example.baeza.fatherhome.ui.manager;

import com.example.baeza.fatherhome.ui.model.BibleTextContent;

import java.util.List;

public interface SosListManager {

    interface Model{
        void getSosListModel(String language, int moodPosition);
        void closeDatabaseListenerModel();
    }
    interface View{
        void getSosListView(List<BibleTextContent> bibleTextContentList);
    }
    interface Presenter{
        void getSosListModel(String language, int moodPosition);
        void closeDatabaseListenerModel();

        void getSosListView(List<BibleTextContent> bibleTextContentList);
    }
}
