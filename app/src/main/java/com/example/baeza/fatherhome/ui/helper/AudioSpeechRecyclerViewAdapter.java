package com.example.baeza.fatherhome.ui.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.Speech;
import com.example.baeza.fatherhome.ui.view.audioSpeech.AudioSpeechDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudioSpeechRecyclerViewAdapter extends RecyclerView.Adapter<AudioSpeechRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Speech> mSpeechList;

    public AudioSpeechRecyclerViewAdapter(List<Speech> speechList, Context context){
        this.mContext = context;
        this.mSpeechList = speechList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_speech_list;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mSpeechList != null && mSpeechList.size() > 0) {

            holder.tvTitle.setText(mSpeechList.get(position).getTitle());
            holder.tvPastorName.setText(mSpeechList.get(position).getPastorName());
            Picasso.get()
                    .load(mSpeechList.get(position).getImageUrl())
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .error(R.drawable.ic_person_black_24dp)
                    .transform(new CircleTransform())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(mSpeechList != null && mSpeechList.size() > 0){
            return mSpeechList.size();
        }else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView tvPastorName, tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        tvPastorName = itemView.findViewById(R.id.tv_pastor_name);
        tvTitle = itemView.findViewById(R.id.tv_title);

        itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Speech speech = mSpeechList.get(clickedPosition);

            Intent intent = new Intent(mContext, AudioSpeechDetailActivity.class);
            intent.putExtra(AudioSpeechDetailActivity.SPEECH_KEY, speech);
            mContext.startActivity(intent);

        }
    }

    public void incomingSpeechList(List<Speech> speechList){
        if(speechList != null){
            mSpeechList = speechList;
            this.notifyDataSetChanged();
        }
    }
}
