package com.example.baeza.fatherhome.ui.view.sos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SosListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public final static String MOOD_POSITION_KEY = "mood_position_key";
    private int moodPosition;

    private SosListFragment mSosListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_list);

        ButterKnife.bind(this);
        mSosListFragment = new SosListFragment();

        if(getIntent() != null){
            moodPosition = getIntent().getIntExtra(MOOD_POSITION_KEY, 0);

            Bundle bundle = new Bundle();
            bundle.putInt(MOOD_POSITION_KEY, moodPosition);

            mSosListFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mSosListFragment)
                    .commit();
        }
        initToolbar();
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getTitleFromMood(moodPosition));
    }

    private String getTitleFromMood(int moodPosition){
        switch (moodPosition){
            case Constants.SOS_DEPRESSION:{return getString(R.string.sosfragment_depression);}
            case Constants.SOS_ANGRY:{ return   getString(R.string.angry); }
            case Constants.SOS_TIRED:{ return  getString(R.string.tired); }
            case Constants.SOS_ALONE:{ return  getString(R.string.alone); }
            case Constants.SOS_SEEKING_PEACE:{ return getString(R.string.seeking_peace); }
            case Constants.SOS_HOPE:{ return getString(R.string.hope); }
            case Constants.SOS_WEAK:{ return getString(R.string.weak); }
            case Constants.SOS_SAD:{ return getString(R.string.sad); }
            case Constants.SOS_ECONOMY:{ return getString(R.string.economy); }
            case Constants.SOS_LOST:{ return getString(R.string.lost); }
            case Constants.SOS_LOOKING_FOR_FORGIVNESS:{ return getString(R.string.looking_for_forgiveness); }
            case Constants.SOS_HOW_TO_FORGIVE:{ return getString(R.string.how_to_forgive); }
            case Constants.SOS_FORGETTING:{ return getString(R.string.forgetting); }
            case Constants.SOS_FEAR_OF_TOMORROW:{ return getString(R.string.fear_of_tomorrow); }
            case Constants.SOS_SICK:{ return getString(R.string.sick); }
            case Constants.SOS_WAR:{ return getString(R.string.war); }
            default: return getString(R.string.app_name);
        }
    }
}
