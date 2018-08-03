package com.example.baeza.fatherhome.ui.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.Pastor;
import com.example.baeza.fatherhome.ui.view.ourPastor.PastorDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

public class PastorListRecyclerViewAdapter extends RecyclerView.Adapter<PastorListRecyclerViewAdapter.ViewHolder>{

    private List<Pastor> mPastorList;
    private Context mContext;

    public PastorListRecyclerViewAdapter(List<Pastor> pastorList, Context context){
        this.mPastorList = pastorList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_pastor_list;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mPastorList != null && mPastorList.size() >0) {

            Timber.d("image photo pastor "+mPastorList.get(position).getImageUrl());

            holder.tvPastorName.setText(mPastorList.get(position).getName());
            Picasso.get()
                    .load(mPastorList.get(position).getImageUrl())
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .error(R.drawable.ic_person_black_24dp)
                    .transform(new CircleTransform())
                    .into(holder.mImageViewPastor);
        }
    }

    @Override
    public int getItemCount() {
        if(mPastorList != null && mPastorList.size()>0){
            return  mPastorList.size();
        } else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mImageViewPastor;
        TextView tvPastorName;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewPastor = itemView.findViewById(R.id.imageView);
            tvPastorName = itemView.findViewById(R.id.tv_pastor_name);

            mImageViewPastor.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putParcelable(PastorDetailActivity.PASTOR_DETAIL_KEY, mPastorList.get(clickedPosition));

            Intent intent = new Intent(mContext, PastorDetailActivity.class);
            intent.putExtra(PastorDetailActivity.PASTOR_DETAIL_KEY, bundle);
            mContext.startActivity(intent);
        }
    }

    public void incomingPastorlist(List<Pastor> pastorList){
        this.mPastorList = pastorList;
        if(pastorList != null){
            this.notifyDataSetChanged();
        }
    }
}
