package com.example.baeza.fatherhome.network;

public interface JsonRequestManager {
    interface Model {
        void requestJsonModel(String version, String book, int chapter, String apiKey);
    }

    interface View {
        void refreshTextView(String result);
    }

    interface Presenter {
        void requestJsonModel(String version, String book, int chapter, String apiKey);
        void refreshTextView(String result);
    }
}
