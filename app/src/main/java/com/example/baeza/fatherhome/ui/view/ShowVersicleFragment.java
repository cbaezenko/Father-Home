package com.example.baeza.fatherhome.ui.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowVersicleFragment extends Fragment {

    @BindView(R.id.textView_versicle)
    TextView mTextViewVersicle;

    private BibleTextContent mBibleTextContent;

    public ShowVersicleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_versicle, container, false);
        ButterKnife.bind(this, rootView);

        if(getArguments() != null){
            mBibleTextContent = getArguments().getParcelable(ShowVersicleActivity.BUNDLE_KEY_SHOW_VERSICLE);
            mTextViewVersicle.setText(mBibleTextContent.getDescription());
        }

        return rootView;
    }
}
