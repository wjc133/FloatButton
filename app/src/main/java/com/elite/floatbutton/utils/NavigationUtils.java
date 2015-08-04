package com.elite.floatbutton.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.elite.floatbutton.R;
import com.elite.floatbutton.ui.AppBarActivity;
import com.elite.floatbutton.ui.MainActivity;
import com.elite.floatbutton.ui.fragment.*;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/3.
 */
public class NavigationUtils {
    private NavigationUtils(){

    }

    public static void replaceFragment(Activity activity, String name) {
        activity.getFragmentManager().beginTransaction().replace(R.id.main_content, Fragment.instantiate(activity,name)).commit(); //// FIXME: 2015/8/3 不能用
    }

    public static void replaceFragment(Activity activity,Fragment fragment) {
        activity.getFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
    }

    public static void addFragment(Activity activity, String name) {
        activity.getFragmentManager().beginTransaction().add(R.id.main_content, Fragment.instantiate(activity, name)).commit();  //// FIXME: 2015/8/3 不能用
    }

    public static void addFragment(Activity activity, Fragment fragment) {
        activity.getFragmentManager().beginTransaction().add(R.id.main_content, fragment).commit();
    }

    public static void startActivity(Activity activity, Class<? extends Activity> activityClass) {
        Intent intent=new Intent(activity,activityClass);
        activity.startActivity(intent);
    }
}
