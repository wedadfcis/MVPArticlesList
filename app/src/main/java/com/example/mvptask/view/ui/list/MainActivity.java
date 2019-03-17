package com.example.mvptask.view.ui.list;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mvptask.R;
import com.example.mvptask.common.Constants;
import com.example.mvptask.common.Utilities;
import com.example.mvptask.base.BaseActivity;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ArticlesPresenter articlesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar((Toolbar) findViewById(R.id.toolbar),R.color.black,getResources().getString(R.string.app_name),true,true);
        initializeViews();
        setListeners();
        displayArticleListFragment();
    }

    @Override
    protected void initializeViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);

    }

    @Override
    protected void setListeners() {
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void displayArticleListFragment() {
        /// add fragment
        ArticleListFragment articleListFragment = new ArticleListFragment();
        articlesPresenter = new ArticlesPresenter(articleListFragment,getApplicationContext());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, articleListFragment, Constants.FragmentTags.ARTICLE_LIST_TAG).commit();
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_explore) {
            Utilities.displayToast(item.getTitle().toString(), this);
        } else if (id == R.id.nav_livechat) {
            Utilities.displayToast(item.getTitle().toString(), this);
        } else if (id == R.id.nav_gallery) {
            Utilities.displayToast(item.getTitle().toString(), this);
        } else if (id == R.id.nav_wishlist) {
            Utilities.displayToast(item.getTitle().toString(), this);
        } else if (id == R.id.nav_emagazine) {
            Utilities.displayToast(item.getTitle().toString(), this);
        }

        //close drawer after select item
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
