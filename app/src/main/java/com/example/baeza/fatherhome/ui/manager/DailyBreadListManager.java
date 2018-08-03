package com.example.baeza.fatherhome.ui.manager;

import com.example.baeza.fatherhome.ui.model.BibleTextContent;

import java.util.List;

public interface DailyBreadListManager {

    interface Model{
        void getDailyListModel(String language);
        void closeDatabaseListenerModel();
    }
    interface View{
        void getDailyListView(List<BibleTextContent> bibleTextContentList);

    }
    interface Presenter{
        void getDailyListModel(String language);
        void closeDatabaseListenerModel();

        void getDailyListView(List<BibleTextContent> bibleTextContentList);
    }
}
