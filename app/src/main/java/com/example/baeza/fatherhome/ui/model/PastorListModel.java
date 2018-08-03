package com.example.baeza.fatherhome.ui.model;

import android.content.Context;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.manager.PastorListManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class PastorListModel implements PastorListManager.Model{

    private PastorListManager.Presenter presenter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Context mContext;

    private List<Pastor> mPastorList;

    public PastorListModel(PastorListManager.Presenter presenter, Context context){
        this.presenter = presenter;
        mPastorList = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public void getPastorListModel(String language) {
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
        mDatabaseReference = mFirebaseDatabase.getReference().child(language).child(mContext.getString(R.string.our_pastors_firebase_node));
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
        Pastor pastor = dataSnapshot.getValue(Pastor.class);
        mPastorList.add(pastor);
        presenter.getPastorListView(mPastorList);
    }
}
