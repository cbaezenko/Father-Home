package com.example.baeza.fatherhome.ui.view.about;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        AboutFragment aboutFragment = new AboutFragment();
        if(mToolbar !=null) {
            setToolbar(getResources().getString(R.string.about_father_home));
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, aboutFragment)
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
