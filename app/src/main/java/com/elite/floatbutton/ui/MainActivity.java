package com.elite.floatbutton.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.elite.floatbutton.R;
import com.elite.floatbutton.ui.fragment.CardFilpDemoFragment;
import com.elite.floatbutton.ui.fragment.DevicesFragment;
import com.elite.floatbutton.ui.fragment.InfoFragment;
import com.elite.floatbutton.utils.NavigationUtils;


public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private boolean needBack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        configView();

    }

    private void configView() {
        mToolbar.setTitle(R.string.main_action_title);
//        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(mToolbar);

        //单独使用ToolBar
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(mNavigationView);

        NavigationUtils.addFragment(this, new InfoFragment());
    }

    private void setupDrawerContent(final NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem prevItem = view.getMenu().findItem(R.id.navi_info);

            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (prevItem != null) {
                    prevItem.setChecked(false);
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                prevItem = menuItem;

                switch (menuItem.getItemId()) {
                    case R.id.navi_info:
                        NavigationUtils.replaceFragment(MainActivity.this, new InfoFragment());
                        break;
                    case R.id.navi_devices:
                        NavigationUtils.replaceFragment(MainActivity.this, new DevicesFragment());
                        break;
                    case R.id.navi_theme_light:
                        NavigationUtils.startActivity(MainActivity.this, AppBarActivity.class);
                        break;
                    case R.id.navi_theme_movie:
                        NavigationUtils.startActivity(MainActivity.this, ViewAnimActivity.class);
                        break;
                    case R.id.navi_theme_night:
                        NavigationUtils.startActivity(MainActivity.this,TransitionActivity.class);
                        break;
                    default:
                        NavigationUtils.replaceFragment(MainActivity.this, new InfoFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        if (id == R.id.action_change) {
            if (needBack){
                getFragmentManager().popBackStack();
                needBack=false;
                return true;
            }
            needBack=true;
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out, R.animator.card_flip_right_in, R.animator.card_flip_right_out)
                    .replace(R.id.main_content, new CardFilpDemoFragment()).addToBackStack(null).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
