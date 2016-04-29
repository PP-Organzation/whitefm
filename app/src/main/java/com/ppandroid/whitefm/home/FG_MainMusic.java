package com.ppandroid.whitefm.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antonioleiva.mvpexample.app.R;
import com.ppandroid.whitefm.base.FG_Base;
import com.ppandroid.whitefm.view.MusicPlayerView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yeqinfu on 2016/4/7.
 * main fg
 */
public class FG_MainMusic extends FG_Base {

    @Bind(R.id.mpv)
    MusicPlayerView mpv;
    @Override
    public int getFragmentLayout() {
        return R.layout.fg_main_music;
    }

    @Override
    public void afterViews() {
        mpv.setCoverURL("https://upload.wikimedia.org/wikipedia/en/b/b3/MichaelsNumberOnes.JPG");
    }
    @OnClick(R.id.mpv)
    public void onClick(View v){
        if (mpv.isRotating()) {
            mpv.stop();
        } else {
            mpv.start();
        }
    }
}
