package com.elite.floatbutton.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.elite.floatbutton.R;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/7.
 */
public class MusicActivity extends Activity {

    private ImageView image;

    private ExitActivityTransition exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        image = (ImageView) findViewById(R.id.image_scenery);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exit = ActivityTransition.with(getIntent()).to(image).duration(300).start(savedInstanceState);
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            exit.exit(this);
        } else {
            super.onBackPressed();
        }
    }
}
