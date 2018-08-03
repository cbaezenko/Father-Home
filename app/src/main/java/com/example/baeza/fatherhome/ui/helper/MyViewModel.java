package com.example.baeza.fatherhome.ui.helper;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.baeza.fatherhome.database.AppDatabase;
import com.example.baeza.fatherhome.database.FavoriteEntry;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private LiveData<List<FavoriteEntry>> mListLiveData;

    public MyViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        mListLiveData = database.mFavoriteDao().loadAllFavorite();
    }

    public LiveData<List<FavoriteEntry>> getListLiveData() {
        return mListLiveData;
    }
}
