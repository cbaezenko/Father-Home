package com.example.baeza.fatherhome.ui.view.ourPastor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.Pastor;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastorDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar_image)
    ImageView mAppBarImage;

    public static final String PASTOR_DETAIL_KEY = "pastor_detail_key";
    private Pastor mPastor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastor_detail);

        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra(PASTOR_DETAIL_KEY);
            mPastor = bundle.getParcelable(PASTOR_DETAIL_KEY);

            ButterKnife.bind(this);

            setToolbar();
            setImageAppBar();

            PastorDetailFragment pastorDetailFragment = new PastorDetailFragment();
            pastorDetailFragment.setArguments(bundleToFragment(mPastor));

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, pastorDetailFragment)
                    .commit();
        }
    }

    private void setImageAppBar(){
        Picasso.get()
                .load(mPastor.getImageUrl())
                .placeholder(R.drawable.ic_person_black_24dp)
                .error(R.drawable.ic_person_black_24dp)
                .into(mAppBarImage);
    }

    private Bundle bundleToFragment(Pastor pastor) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PASTOR_DETAIL_KEY, pastor);
        return bundle;
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mPastor.getName());
    }
}
