package com.example.baeza.fatherhome.ui.model;

import android.content.Context;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.manager.SosListManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SosListModel implements SosListManager.Model {

    private SosListManager.Presenter mPresenter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Context mContext;

    private List<BibleTextContent> mBibleTextContentList;


    public SosListModel(SosListManager.Presenter presenter, Context context){
        this.mPresenter = presenter;
        mBibleTextContentList = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public void getSosListModel(String language, int moodPosition) {
        getInfoFromDatabase(language, moodPosition);
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void getInfoFromDatabase(String language, int moodPosition){

        String moodPositionString = selectMood(moodPosition);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(language).child(mContext.getString(R.string.sos_bible_firebase_node)).child(moodPositionString);
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
        BibleTextContent bibleTextContent = dataSnapshot.getValue(BibleTextContent.class);
        mBibleTextContentList.add(bibleTextContent);
        mPresenter.getSosListView(mBibleTextContentList);
    }

    private String selectMood(int moodPosition){
        switch (moodPosition){
            case Constants.SOS_DEPRESSION:{
                return Constants.STRING_SOS_DEPRESSION;
            }
            case Constants.SOS_ANGRY:{
                return Constants.STRING_SOS_ANGRY;
            }
            case Constants.SOS_TIRED:{
                return Constants.STRING_SOS_TIRED;
            }
            case Constants.SOS_ALONE:{
                return Constants.STRING_SOS_ALONE;
            }
            case Constants.SOS_SEEKING_PEACE:{
                return Constants.STRING_SOS_SEEKING_PEACE;
            }
            case Constants.SOS_HOPE:{
                return Constants.STRING_SOS_HOPE;
            }
            case Constants.SOS_WEAK:{
                return Constants.STRING_SOS_WEAK;
            }
            case Constants.SOS_SAD:{
                return Constants.STRING_SOS_SAD;
            }
            case Constants.SOS_ECONOMY:{
                return Constants.STRING_SOS_ECONOMY;
            }
            case Constants.SOS_LOST:{
                return Constants.STRING_SOS_LOST;
            }
            case Constants.SOS_LOOKING_FOR_FORGIVNESS:{
                return Constants.STRING_SOS_LOOKING_FOR_FORGIVNESS;
            }
            case Constants.SOS_HOW_TO_FORGIVE:{
                return Constants.STRING_SOS_HOW_TO_FORGIVE;
            }
            case Constants.SOS_FORGETTING:{
                return Constants.STRING_SOS_FORGETTING;
            }
            case Constants.SOS_FEAR_OF_TOMORROW:{
                return Constants.STRING_SOS_FEAR_OF_TOMORROW;
            }
            case Constants.SOS_SICK:{
                return Constants.STRING_SOS_SICK;
            }
            case Constants.SOS_WAR:{
                return Constants.STRING_SOS_WAR;
            }
            default:{
                return "";
            }
        }
    }
}
