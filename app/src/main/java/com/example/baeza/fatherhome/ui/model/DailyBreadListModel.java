package com.example.baeza.fatherhome.ui.model;

import android.content.Context;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.manager.DailyBreadListManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DailyBreadListModel implements DailyBreadListManager.Model {

    private DailyBreadListManager.Presenter mPresenter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Context mContext;

    private List<BibleTextContent> mBibleTextContentList;

    public DailyBreadListModel(DailyBreadListManager.Presenter presenter, Context context){
        this.mPresenter = presenter;
        mBibleTextContentList = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public void getDailyListModel(String language) {
        getInfoFromDatabase(language);
    }

    @Override
    public void closeDatabaseListenerModel() {
        if(mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    private void getInfoFromDatabase(String language){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(language).child(mContext.getString(R.string.dailybread_firebase_node));
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
        mPresenter.getDailyListView(mBibleTextContentList);
    }
}
