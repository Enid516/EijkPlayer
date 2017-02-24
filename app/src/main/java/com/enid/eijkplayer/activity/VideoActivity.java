package com.enid.eijkplayer.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.enid.eijkplayer.R;


import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * Created by big_love on 2017/2/21.
 */

public class VideoActivity extends AppCompatActivity {
    private IjkVideoView mVideoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //init view
        mVideoView = (IjkVideoView) findViewById(R.id.ijkVideoView);
        TableLayout hudView = (TableLayout) findViewById(R.id.hud_view);

        //init ijkplayer
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        String path = Environment.getExternalStorageDirectory() + "/test.mp4";
        mVideoView.setVideoPath(path);
//        mVideoView.setHudView(hudView);
        AndroidMediaController mMediaController = new AndroidMediaController(this);


        mVideoView.setMediaController(mMediaController);
        mVideoView.start();
    }
}
