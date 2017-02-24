package com.enid.eijkplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.enid.eijkplayer.R;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * Created by big_love on 2017/2/22.
 */

public class LiveActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private String videoPath;
    private boolean  mBackPressed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        videoPath = getIntent().getStringExtra("stream_addr");
        mVideoView = (IjkVideoView) findViewById(R.id.ijkVideoView);

        //init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        if (videoPath != null) {
            mVideoView.setVideoPath(videoPath);
        }
        mVideoView.start();

    }
    public static void actionStart(Context context,String stream_addr) {
        Intent starter = new Intent(context, LiveActivity.class);
        starter.putExtra("stream_addr",stream_addr);
        context.startActivity(starter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mBackPressed = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBackPressed && !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
