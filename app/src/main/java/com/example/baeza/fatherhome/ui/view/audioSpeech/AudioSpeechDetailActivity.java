package com.example.baeza.fatherhome.ui.view.audioSpeech;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.model.Speech;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioSpeechDetailActivity extends AppCompatActivity {

    public static final String SPEECH_KEY = "speech_key";
    private Speech mSpeech;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_speech_detail);

        ButterKnife.bind(this);

        setToolbar(getString(R.string.speech));
        if(savedInstanceState != null){ return; }
        else {
            AudioSpeechDetailFragment mAudioSpeechDetailFragment = new AudioSpeechDetailFragment();
            if (getIntent() != null) {
                mSpeech = getIntent().getParcelableExtra(SPEECH_KEY);
                Bundle bundle = new Bundle();
                bundle.putParcelable(SPEECH_KEY, mSpeech);
                mAudioSpeechDetailFragment.setArguments(bundle);
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mAudioSpeechDetailFragment)
                    .commit();
        }
    }

    private void setToolbar(String toolbarTitle) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(toolbarTitle);
    }
}
