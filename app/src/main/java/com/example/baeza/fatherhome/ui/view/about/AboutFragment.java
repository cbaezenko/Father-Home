package com.example.baeza.fatherhome.ui.view.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutFragment extends Fragment {

    @BindView(R.id.imageButtonPrincipio)
    ImageButton imageButtonPrincipio;
    @BindView(R.id.imageButtonEstructura)
    ImageButton imageButtonEstructura;
    @BindView(R.id.imageButtonVision)
    ImageButton imageButtonVision;

    private String[] descriptionArray;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.imageButtonPrincipio)
    public void clickPrincipios(){
        descriptionArray = getResources().getStringArray(R.array.principios);
        startNewActivity(getString(R.string.about_zennosti), descriptionArray); }

    @OnClick(R.id.imageButtonEstructura)
    public void clickEstructura(){
        descriptionArray = getResources().getStringArray(R.array.estructura);
        startNewActivity(getString(R.string.estructura), descriptionArray); }

    @OnClick(R.id.imageButtonVision)
    public void clickVision(){
        descriptionArray = getResources().getStringArray(R.array.vision);
        startNewActivity(getString(R.string.about_vision), descriptionArray); }

    private void startNewActivity(String string, String[] descriptionArray){
        Intent intent = new Intent(getContext(), AboutDetailActivity.class);
        intent.putExtra(AboutDetailActivity.ABOUT_TITLE_KEY, string);
        intent.putExtra(AboutDetailActivity.ABOUT_DESCRIPTION_KEY, descriptionArray);
        getContext().startActivity(intent);
    }
}
