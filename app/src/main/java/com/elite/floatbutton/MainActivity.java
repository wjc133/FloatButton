package com.elite.floatbutton;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.elite.floatbutton.adapter.MutlipleItemAdapter;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        configView();

    }

    private void configView() {
        mToolbar.setTitle(R.string.main_action_title);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
//        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(mToolbar);

        final ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
        mRecyclerView.setAdapter(new MutlipleItemAdapter(this));

        setupDrawerContent(mNavigationView);

    }

    private void setupDrawerContent(final NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem prevItem=view.getMenu().findItem(R.id.navi_info);
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (prevItem != null) {
                    prevItem.setChecked(false);
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                prevItem=menuItem;
                return true;
            }
        });
    }

    private void findView() {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mRecyclerView=(RecyclerView)findViewById(R.id.cyc_main);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_main);
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

        return super.onOptionsItemSelected(item);
    }
}
