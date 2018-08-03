package com.example.baeza.fatherhome.ui.view.ourPastor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.Pastor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastorDetailFragment extends Fragment {

    @BindView(R.id.tv_pastor_description)
    TextView tvPastorDescription;
    private Pastor mPastor;

    public PastorDetailFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pastor_detail, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            if (getArguments().containsKey(PastorDetailActivity.PASTOR_DETAIL_KEY)) {
                Bundle bundle = getArguments();
                mPastor = bundle.getParcelable(PastorDetailActivity.PASTOR_DETAIL_KEY);
            }
        }
        tvPastorDescription.setText(mPastor.getDescription().replace("\n","\n"));
        return view;
    }
}
