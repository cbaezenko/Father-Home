package com.example.baeza.fatherhome.ui.view.audioSpeech;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baeza.fatherhome.R;
import com.example.baeza.fatherhome.ui.Constants;
import com.example.baeza.fatherhome.ui.helper.AudioEvent;
import com.example.baeza.fatherhome.ui.helper.BroadCastReceiverNotificationDelete;
import com.example.baeza.fatherhome.ui.model.Speech;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AudioSpeechDetailFragment extends Fragment implements ExoPlayer.EventListener {

    @BindView(R.id.exo_player)
    SimpleExoPlayerView mSimpleExoPlayerView;
    @BindView(R.id.tv_title)
    TextView mTextViewTitle;
    @BindView(R.id.tv_pastor_name)
    TextView mTextViewPastorName;
    @BindView(R.id.tv_description)
    TextView mTextViewDescription;

    private Speech mSpeech;
    private long playerPosition;
    private boolean playerState;

    private SimpleExoPlayer mExoPlayer;
    private static final String USER_AGENT = "FatherHome";
    private static final String TAG = AudioSpeechListFragment.class.getSimpleName();
    private PlaybackStateCompat.Builder mStateBuilder;
    private NotificationManager mNotificationManager;
    private static MediaSessionCompat mMediaSession;

    private static final String POSITION = "position";
    private static final String SPEECH = "speech";
    private static final String STATE = "state";

    public AudioSpeechDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_speech_detail, container, false);

        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            mSpeech = savedInstanceState.getParcelable(SPEECH);
            playerPosition = savedInstanceState.getLong(POSITION);
            playerState = savedInstanceState.getBoolean(STATE);
        } else {
            if (getArguments() != null) {
                mSpeech = getArguments().getParcelable(AudioSpeechDetailActivity.SPEECH_KEY);
            }
        }
        mTextViewTitle.setText(mSpeech.getTitle());
        mTextViewPastorName.setText(mSpeech.getPastorName());
        mTextViewDescription.setText(mSpeech.getDescription());
        setImageExoPlayer(mSpeech.getPastorCode());
        initializeMediaSession();
        initializePlayer(mSpeech.getAudiolink());
        return view;
    }

    private void setImageExoPlayer(int pastorCode) {
        switch (pastorCode) {
            case Constants.VLADIMIR_ZUEV: {
                mSimpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),
                        R.drawable.vladimir_zuev));
                break;
            }
            case Constants.NO_PHOTO: {
                mSimpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(),
                        R.drawable.logofatherhome));
                break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        playerPosition = mExoPlayer.getCurrentPosition();
        playerState = mExoPlayer.getPlayWhenReady();

        outState.putLong(POSITION, playerPosition);
        outState.putBoolean(STATE, playerState);
        outState.putParcelable(SPEECH, mSpeech);
        super.onSaveInstanceState(outState);

    }

    private void showNotification(PlaybackStateCompat state) {

        final String CHANNEL_ID = getContext().getResources().getString(R.string.channel_id);

        Intent intent = new Intent(getContext(), BroadCastReceiverNotificationDelete.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());

        int icon;
        String play_pause;
        if (state.getState() == PlaybackStateCompat.STATE_PLAYING) {
            icon = R.drawable.exo_controls_pause;
            play_pause = getContext().getString(R.string.pause);
        } else {
            icon = R.drawable.exo_controls_play;
            play_pause = getContext().getString(R.string.play);
        }

        NotificationCompat.Action playPauseAction = new NotificationCompat.Action(
                icon, play_pause,
                MediaButtonReceiver.buildMediaButtonPendingIntent(getContext(),
                        PlaybackStateCompat.ACTION_PLAY_PAUSE));

        NotificationCompat.Action restartAction = new android.support.v4.app.NotificationCompat
                .Action(R.drawable.exo_controls_previous, getString(R.string.restart),
                MediaButtonReceiver.buildMediaButtonPendingIntent
                        (getContext(), PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS));

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (getContext(), 0, new Intent(getContext(), AudioSpeechListActivity.class), 0);

        builder.setContentTitle(mSpeech.getTitle())
                .setContentText(mSpeech.getPastorName())
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.logofatherhome)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(restartAction)
                .addAction(playPauseAction)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mMediaSession.getSessionToken())
                        .setShowActionsInCompactView(0, 1));

        builder.setDeleteIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            mNotificationManager = getContext().getSystemService(NotificationManager.class);
            mNotificationManager.createNotificationChannel(channel);
            builder.setChannelId(CHANNEL_ID);
        }
        mNotificationManager.notify(0, builder.build());
        builder.build();
    }

    private void initializeMediaSession() {
        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(getContext(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());

        // MySessionCallback has methods that handle callbacks from a media controller.
        mMediaSession.setCallback(new MySessionCallback());
        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
        showNotification(mStateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity() {
    }

    /**
     * Media Session Callbacks, where all external clients control the player.
     */
    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    private void initializePlayer(String uriString) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mSimpleExoPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);
            mExoPlayer.prepare(buildMediaSource(uriString));

            if (playerPosition != 0) {
                mExoPlayer.seekTo(playerPosition);
                mExoPlayer.setPlayWhenReady(playerState);
            } else {
                mExoPlayer.setPlayWhenReady(true);
            }
        }
    }

    private MediaSource buildMediaSource(String uriString) {

        BandwidthMeter bandWidthMeter = new DefaultBandwidthMeter();
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                USER_AGENT,
                (TransferListener<? super DataSource>) bandWidthMeter);
        DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();

        return new ExtractorMediaSource(Uri.parse(uriString),
                dataSourceFactory,
                defaultExtractorsFactory,
                null,
                null);
    }

    private void releasePlayer() {
        mNotificationManager.cancelAll();
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    /*
     * Broadcast Receiver registered to receive the MEDIA_BUTTON intent coming from clients.
     * */
    public static class MediaReceiver extends BroadcastReceiver {
        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        releasePlayer();
        mMediaSession.setActive(false);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AudioEvent event) {
        releasePlayer();
        Intent intent = new Intent(getContext(), AudioSpeechListActivity.class);
        startActivity(intent);
    }
}