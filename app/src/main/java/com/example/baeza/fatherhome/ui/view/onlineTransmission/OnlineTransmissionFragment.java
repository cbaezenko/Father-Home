package com.example.baeza.fatherhome.ui.view.onlineTransmission;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnlineTransmissionFragment extends Fragment {

    @BindView(R.id.webView)
    WebView mWebView;

    public OnlineTransmissionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_transmission, container, false);
        ButterKnife.bind(this, view);

        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl(Constants.FATHER_HOME_YOUTUBE);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
