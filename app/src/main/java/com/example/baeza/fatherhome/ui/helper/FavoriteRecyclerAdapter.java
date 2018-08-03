package com.example.baeza.fatherhome.ui.helper;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.database.AppDatabase;
import com.example.baeza.fatherhome.database.FavoriteEntry;

import java.util.List;

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoriteRecyclerAdapter.ViewHolder>{

    private List<FavoriteEntry> mFavoriteEntries;
    private Context mContext;
    private AppDatabase mAppDatabase;

    public FavoriteRecyclerAdapter(List<FavoriteEntry> favoriteEntryList){
        this.mFavoriteEntries = favoriteEntryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mAppDatabase = AppDatabase.getInstance(parent.getContext());

        int layout = R.layout.item_card_bible;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mFavoriteEntries != null && mFavoriteEntries.size() > 0){
            holder.mTextViewVersicle.setText(mFavoriteEntries.get(position).getDescription());
            holder.mTextViewAddress.setText(mFavoriteEntries.get(position).getAddress());
        }
    }

    @Override
    public int getItemCount() {
        if(mFavoriteEntries != null && mFavoriteEntries.size() > 0){
            return  mFavoriteEntries.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewVersicle, mTextViewAddress;
        ImageButton mImageButtonFav, mImageButtonShare;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewVersicle = itemView.findViewById(R.id.textView_versicle);
            mTextViewAddress = itemView.findViewById(R.id.textViewAddress);

            mImageButtonShare = itemView.findViewById(R.id.button_share);
            mImageButtonFav = itemView.findViewById(R.id.button_fav);

            mImageButtonShare.setOnClickListener(this);
            mImageButtonFav.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            int clickedPosition = getAdapterPosition();
            switch (view.getId()){
                case R.id.button_fav:{
                    final FavoriteEntry favoriteEntry = new FavoriteEntry(mFavoriteEntries.get(clickedPosition).getDescription(),
                            mFavoriteEntries.get(clickedPosition).getAddress(),
                            mFavoriteEntries.get(clickedPosition).getData());

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            boolean exist = mAppDatabase.mFavoriteDao().checkFavorite(favoriteEntry.getAddress());
                            if (exist) {
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAppDatabase.mFavoriteDao().deleteFavorite(favoriteEntry.getAddress());
                                        String textFavorite = mContext.getString(R.string.deleted_from_favorites);
                                        Snackbar snackbar = Snackbar.make(view, textFavorite, Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                });
                            } else {
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAppDatabase.mFavoriteDao().insertFavorite(favoriteEntry);
                                        String textFavorite = mContext.getString(R.string.added_to_favorites);
                                        Snackbar snackbar = Snackbar.make(view, textFavorite, Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                });
                            }
                        }
                    });
                    break;
                }
                case R.id.button_share:{

                    String shareText = mFavoriteEntries.get(clickedPosition).getDescription() +
                            " "+ mFavoriteEntries.get(clickedPosition).getAddress();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType(mContext.getString(R.string.share_message_type));
                    intent.putExtra(Intent.EXTRA_TEXT, shareText);
                    mContext.startActivity(Intent.createChooser(intent, mContext.getResources().getString(R.string.share_with)));
                    break;
                }
            }
        }
    }

    public void refreshRecyclerView(){
     this.notifyDataSetChanged();
    }
}
