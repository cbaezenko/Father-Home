package com.example.baeza.fatherhome.ui.view.ourPastor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastorListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastor_list);

        ButterKnife.bind(this);
        setToolbar();

        PastorListFragment pastorListFragment = new PastorListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, pastorListFragment)
                .commit();
    }
    private void setToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert  actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.nav_our_pastors));
    }
}
