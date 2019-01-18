package com.example.vandanasri.exoplayer;

import android.net.Uri;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SimpleExoPlayer sep;
    SimpleExoPlayerView sepV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sepV = findViewById(R.id.exo);
        BandwidthMeter bandwidthMeter;
        bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        sep = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        Uri uri=Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4\n");
        DefaultHttpDataSourceFactory httpDataSourceFactory=new DefaultHttpDataSourceFactory("tom");
        ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();
        MediaSource mediaSource=new ExtractorMediaSource(uri,httpDataSourceFactory,extractorsFactory,null,null);
        sepV.setPlayer(sep);
        sep.prepare(mediaSource);
        sep.setPlayWhenReady(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sep.release();
    }
}

