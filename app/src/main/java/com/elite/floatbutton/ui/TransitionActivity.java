package com.elite.floatbutton.ui;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elite.floatbutton.R;
import com.elite.floatbutton.animator.CustomTransition;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/6.
 */
public class TransitionActivity extends Activity {
    private Scene endScene;
    private Scene startScene;
//    private Fade fade;
    private Transition transition;
    private ViewGroup container;
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        container=(ViewGroup)findViewById(R.id.transition_content);
        getLayoutInflater().inflate(R.layout.transition_scenery, container, true);
        btn_start=(Button)findViewById(R.id.btn_swap_view);

        endScene=Scene.getSceneForLayout(container,R.layout.transition_dog,this);
        startScene=Scene.getSceneForLayout(container,R.layout.transition_scenery,this);
//        fade=new Fade();
        transition=new CustomTransition();

        btn_start.setOnClickListener(new View.OnClickListener() {
            private boolean isSwaped=false;
            @Override
            public void onClick(View v) {
                if (isSwaped){
                    TransitionManager.go(startScene,transition);
                    isSwaped=false;
                }else {
                    TransitionManager.go(endScene, transition);
                    isSwaped=true;
                }
            }
        });

    }
}
