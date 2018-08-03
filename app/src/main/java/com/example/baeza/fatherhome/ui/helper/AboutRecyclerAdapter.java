package com.example.baeza.fatherhome.ui.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;

public class AboutRecyclerAdapter extends RecyclerView.Adapter<AboutRecyclerAdapter.ViewHolder> {

    private String[] descriptionArray;

    public AboutRecyclerAdapter(String[] descriptionArray) {
        this.descriptionArray = descriptionArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_about;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(descriptionArray != null && descriptionArray.length >0){
            holder.mTextViewDescription.setText(descriptionArray[position]);
        }
    }

    @Override
    public int getItemCount() {
        if (descriptionArray != null && descriptionArray.length > 0) {
            return descriptionArray.length;
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
