package com.example.baeza.fatherhome.ui.view.about;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public final static String ABOUT_DESCRIPTION_KEY = "about_description_key";
    public final static String ABOUT_TITLE_KEY = "about_title_key";

    private AboutDetailFragment aboutDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_detail);

        ButterKnife.bind(this);

        if(getIntent() != null) {

            String title = getIntent().getStringExtra(ABOUT_TITLE_KEY);
            String[] descriptionArray = getIntent().getStringArrayExtra(ABOUT_DESCRIPTION_KEY);

            setToolbar(title);

            aboutDetailFragment = new AboutDetailFragment();
            aboutDetailFragment.setArguments(setBundleToFragment(title, descriptionArray));

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, aboutDetailFragment)
                    .commit();

        }
    }

    private void setToolbar(String toolbarTitle) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);
    }

    private Bundle setBundleToFragment(String title, String[] descriptionArray){
        Bundle bundle = new Bundle();
        bundle.putString(ABOUT_TITLE_KEY,title);
        bundle.putStringArray(ABOUT_DESCRIPTION_KEY, descriptionArray);
        return bundle;
    }
}
