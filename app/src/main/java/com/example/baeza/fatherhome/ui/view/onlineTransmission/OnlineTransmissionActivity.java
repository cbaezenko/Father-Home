package com.example.baeza.fatherhome.ui.view.onlineTransmission;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnlineTransmissionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    OnlineTransmissionFragment onlineTransmissionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_transmission);

        ButterKnife.bind(this);

        setToolbar(getString(R.string.online_transmission));

        onlineTransmissionFragment = new OnlineTransmissionFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, onlineTransmissionFragment)
                .commit();

    }

    private void setToolbar(String toolbarTitle) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);
    }
}
