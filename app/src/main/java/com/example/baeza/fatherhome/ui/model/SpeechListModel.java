package com.example.baeza.fatherhome.ui.model;

import android.content.Context;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.manager.SpeechListManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SpeechListModel implements SpeechListManager.Model{

    private SpeechListManager.Presenter mPresenter;
    private List<Speech> mSpeechList;
    private Context mContext;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    public SpeechListModel(SpeechListManager.Presenter presenter, Context context){
        this.mPresenter = presenter;
        this.mContext = context;
        mSpeechList = new ArrayList<>();
    }

    @Override
    public void getSpeechListModel(String language) { getInfoFromDatabase(language); }

    @Override
    public void closeDatabaseListenerModel() {
        if(mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void getInfoFromDatabase(String language){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(language).child(mContext.getString(R.string.pastor_speech_firebase_node));
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { refreshData(dataSnapshot); }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { refreshData(dataSnapshot); }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { refreshData(dataSnapshot); }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { refreshData(dataSnapshot); }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

    private void refreshData(DataSnapshot dataSnapshot){
        Speech speech = dataSnapshot.getValue(Speech.class);
        if(speech != null && mSpeechList.size() > 0) {
            if (!speech.getTitle().equals(mSpeechList.get(mSpeechList.size() - 1).getTitle())) {
                mSpeechList.add(speech);
                mPresenter.getSpeechListView(mSpeechList); } }
            else {
                mSpeechList.add(speech);
                mPresenter.getSpeechListView(mSpeechList);
            }
    }
}
