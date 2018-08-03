package com.example.baeza.fatherhome.ui.view.audioSpeech;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.helper.AudioSpeechRecyclerViewAdapter;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.manager.SpeechListManager;
import com.example.baeza.fatherhome.ui.model.Speech;
import com.example.baeza.fatherhome.ui.presenter.SpeechListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioSpeechListFragment extends Fragment implements SpeechListManager.View{//}, ExoPlayer.EventListener{//}, AudioSpeechRecyclerViewAdapter.ListItemClickListener, ExoPlayer.EventListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private AudioSpeechRecyclerViewAdapter mAudioSpeechRecyclerViewAdapter;
    private List<Speech> mSpeechList;
    private SpeechListPresenter mPresenter;

    public AudioSpeechListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.getSpeechListModel(Constants.RUSSIAN_LANGUAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_audio_speech_list, container, false);
        ButterKnife.bind(this, rootView);

        mPresenter = new SpeechListPresenter(this, container.getContext());
        mSpeechList = new ArrayList<>();

        initRecyclerView();

        return rootView;
    }

    @Override
    public void getSpeechListView(List<Speech> speechList) {
        mAudioSpeechRecyclerViewAdapter.incomingSpeechList(speechList);
    }

    private void initRecyclerView(){

        mAudioSpeechRecyclerViewAdapter = new AudioSpeechRecyclerViewAdapter(mSpeechList, getContext());//, this);

        mRecyclerView.setAdapter(mAudioSpeechRecyclerViewAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStop(){
        super.onStop();
        mPresenter.closeDatabaseListenerModel();
    }
}
