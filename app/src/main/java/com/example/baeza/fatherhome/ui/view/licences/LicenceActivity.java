package com.example.baeza.fatherhome.ui.view.licences;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LicenceActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private LicenseFragment mLicenseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);

        ButterKnife.bind(this);
        setToolbar(getString(R.string.nav_about_app));

        mLicenseFragment = new LicenseFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mLicenseFragment)
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
