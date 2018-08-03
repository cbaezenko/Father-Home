package com.example.baeza.fatherhome.ui.view.licences;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicenseFragment extends Fragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.listView)
    ListView mListView;

    private final static int ANDROID_LICENSE = 0;
    private final static int BUTTERKNIFE_LICENSE = 1;
    private final static int TIMBER_LICENSE = 2;
    private final static int PICASSO_LICENSE = 3;
    private final static int MATERIAL_DESIGN_ICONS_LICENSE = 4;
    private final static int BIBLE_LICENSE = 5;

    public LicenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_license, container, false);
        ButterKnife.bind(this, view);

        String [] arrayLicences = getContext().getResources().getStringArray(R.array.array_licences);
        initListView(arrayLicences);
        return view;
    }

    private void initListView(String[] values){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, values);
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(arrayAdapter);

     }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case ANDROID_LICENSE:{
                Uri webPage = Uri.parse(getString(R.string.link_apache_license));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            }
            case BUTTERKNIFE_LICENSE:{
                Uri webPage = Uri.parse(getString(R.string.link_apache_license));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            }
            case TIMBER_LICENSE:{
                Uri webPage = Uri.parse(getString(R.string.link_apache_license));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            }
            case PICASSO_LICENSE:{
                Uri webPage = Uri.parse(getString(R.string.link_apache_license));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            }
            case MATERIAL_DESIGN_ICONS_LICENSE:{
                break;
            }
            case BIBLE_LICENSE:{
                Uri webPage = Uri.parse(getString(R.string.bible_link));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
                break;
            }
        }
    }
}
