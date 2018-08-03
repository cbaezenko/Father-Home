package com.example.baeza.fatherhome.ui.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.database.AppDatabase;
import com.example.baeza.fatherhome.database.FavoriteEntry;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;
import com.example.baeza.fatherhome.ui.view.ShowVersicleActivity;

import java.util.List;

public class SosDetailListAdapter extends RecyclerView.Adapter<SosDetailListAdapter.ViewHolder> {

    private Context mContext;
    private AppDatabase mAppDatabase;
    private List<BibleTextContent> mBibleTextContentList;

    public SosDetailListAdapter(Context context, List<BibleTextContent> bibleTextContentList){
        this.mContext = context;
        this.mBibleTextContentList = bibleTextContentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_card_bible;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        mAppDatabase = AppDatabase.getInstance(mContext);

        View view = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mBibleTextContentList != null && mBibleTextContentList.size() >0) {
            holder.mTextViewVersicle.setText(mBibleTextContentList.get(position).getDescription());
            holder.mTextViewAddress.setText(mBibleTextContentList.get(position).getAddress());
        }
    }

    @Override
    public int getItemCount() {
        if(mBibleTextContentList != null && mBibleTextContentList.size() > 0){
            return mBibleTextContentList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button buttonShowMore;
        ImageButton mImageButtonShare, mImageButtonFav;
        TextView mTextViewVersicle, mTextViewAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            buttonShowMore = itemView.findViewById(R.id.button_show_more);
            mTextViewVersicle = itemView.findViewById(R.id.textView_versicle);
            mTextViewAddress = itemView.findViewById(R.id.textViewAddress);
            mImageButtonShare = itemView.findViewById(R.id.button_share);
            mImageButtonFav = itemView.findViewById(R.id.button_fav);

            buttonShowMore.setOnClickListener(this);
            mImageButtonShare.setOnClickListener(this);
            mImageButtonFav.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            int clickedPosition = getAdapterPosition();
            switch (view.getId()){
                case R.id.button_show_more:{
                    Intent intent = new Intent(mContext, ShowVersicleActivity.class);
                    BibleTextContent bibleTextContent = mBibleTextContentList.get(clickedPosition);
                    intent.putExtra(ShowVersicleActivity.BUNDLE_KEY_SHOW_VERSICLE, bibleTextContent);
                    mContext.startActivity(intent);
                    break;
                }
                case R.id.button_fav:{
                    final FavoriteEntry favoriteEntry = new FavoriteEntry(mBibleTextContentList.get(clickedPosition).getDescription(),
                            mBibleTextContentList.get(clickedPosition).getAddress(),
                            mBibleTextContentList.get(clickedPosition).getDate());

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

                    String shareText = mBibleTextContentList.get(clickedPosition).getDescription() +
                            " "+ mBibleTextContentList.get(clickedPosition).getAddress();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType(mContext.getString(R.string.share_message_type));
                    intent.putExtra(Intent.EXTRA_TEXT, shareText);
                    mContext.startActivity(Intent.createChooser(intent, mContext.getResources().getString(R.string.share_with)));
                    break;
                }
            }
        }
    }

    public void refreshRecyclerView(List<BibleTextContent> bibleTextContentList){
        if(bibleTextContentList != null){
            mBibleTextContentList = bibleTextContentList;
            this.notifyDataSetChanged();
        }
    }
}
