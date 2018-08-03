package com.example.baeza.fatherhome.ui.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.BibleTextContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowVersicleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private BibleTextContent mBibleTextContent;

    public static final String BUNDLE_KEY_SHOW_VERSICLE = "bundle_key_show_versicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_versicle);
        ButterKnife.bind(this);

        setToolbar(getResources().getString(R.string.sos_fragment_title));

        if (savedInstanceState == null) {
            if (getIntent() != null) {
                if (getIntent().hasExtra(BUNDLE_KEY_SHOW_VERSICLE)) {
                    if(getIntent().getParcelableExtra(BUNDLE_KEY_SHOW_VERSICLE) != null) {
                        mBibleTextContent = getIntent().getParcelableExtra(BUNDLE_KEY_SHOW_VERSICLE);
                        initFragment(setBundle(mBibleTextContent));
                    }
                }
            }
        } else{
            //recreate when screen is rotate.
        }
    }

    private void setToolbar(String toolbarTitle) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(toolbarTitle);
    }

    private Bundle setBundle(BibleTextContent bibleTextContent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_KEY_SHOW_VERSICLE, bibleTextContent);
        return bundle;
    }

    private void initFragment(Bundle bundle) {
        ShowVersicleFragment versicleFragment = new ShowVersicleFragment();
        versicleFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, versicleFragment)
                .commit();
    }
}
