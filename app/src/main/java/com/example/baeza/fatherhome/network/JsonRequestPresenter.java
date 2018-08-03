package com.example.baeza.fatherhome.network;

public class JsonRequestPresenter implements JsonRequestManager.Presenter {

    private JsonRequestManager.View mView;
    private JsonRequestManager.Model mModel;

    public JsonRequestPresenter(JsonRequestManager.View view){
        this.mView = view;
        this.mModel = new JsonRequestModel(this);
    }

    @Override
    public void requestJsonModel(String version, String book, int chapter, String apiKey) {
        if(mView != null){
            mModel.requestJsonModel(version, book, chapter, apiKey);
        }
    }

    @Override
    public void refreshTextView(String result) {
        if(mView != null){
            mView.refreshTextView(result);
        }
    }
}
