package com.example.baeza.fatherhome.ui.helper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.view.sos.SosListActivity;

public class SosRecyclerAdapter extends RecyclerView.Adapter<SosRecyclerAdapter.ViewHolder> {

    private Context context;

    private final String[] arrayListTitlesSos;
    private final Drawable[] arrayListImageSos;

    public SosRecyclerAdapter(Context context){
        this.context = context;

        Resources resources = context.getResources();
        TypedArray typedArray = resources.obtainTypedArray(R.array.images_sos_array);
        arrayListImageSos = new Drawable[typedArray.length()];
        for (int i =0; i< arrayListImageSos.length; i++){
            arrayListImageSos[i] = typedArray.getDrawable(i);
        }
        arrayListTitlesSos = resources.getStringArray(R.array.title_sos_array);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_sos;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (arrayListImageSos != null && arrayListImageSos .length > 0) {
            holder.imageView.setImageDrawable(arrayListImageSos[position]);
        }
        if(arrayListTitlesSos != null && arrayListTitlesSos.length > 0) {
            holder.textViewTitle.setText(arrayListTitlesSos[position]);
        }
    }

    @Override
    public int getItemCount() {
        return arrayListImageSos.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedButton = getAdapterPosition();

            Intent intent = new Intent(context, SosListActivity.class);
            intent.putExtra(SosListActivity.MOOD_POSITION_KEY, clickedButton);
            context.startActivity(intent);
        }
    }
}
