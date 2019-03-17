package com.example.mvptask.view.ui.details;

import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mvptask.R;
import com.example.mvptask.common.Constants;
import com.example.mvptask.base.BaseActivity;
import com.example.mvptask.data.model.Article;

public class ArticleDetailsActivity extends BaseActivity {
    private ArticleDetailsPresenter articleDetailsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        setToolbar((Toolbar) findViewById(R.id.toolbar),R.color.black,getResources().getString(R.string.app_name),true,true);
        startDetailsFragment();
    }
    private void startDetailsFragment() {
        if (getIntent().getExtras().getParcelable(Constants.Extras.ARTICLE) != null) {
            ArticleDetailsFragment articleDetailsFragment = new ArticleDetailsFragment();
            articleDetailsPresenter = new ArticleDetailsPresenter((Article)getIntent().getExtras().getParcelable(Constants.Extras.ARTICLE),articleDetailsFragment);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, articleDetailsFragment).commit();
        }

    }

    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
