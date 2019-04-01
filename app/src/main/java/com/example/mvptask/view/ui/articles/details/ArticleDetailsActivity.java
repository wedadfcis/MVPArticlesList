package com.example.mvptask.view.ui.articles.details;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mvptask.R;
import com.example.mvptask.base.BaseActivity;
import com.example.mvptask.data.model.dto.Article;
import com.example.mvptask.helper.Constants;

public class ArticleDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        setToolbar((Toolbar) findViewById(R.id.toolbar), R.color.colorPrimary, getResources().getString(R.string.app_name), true, true);
        if (savedInstanceState == null) {
            initFragment();
        }
    }


    @Override
    protected void initializeViews() {

    }

    @Override
    protected void setListeners() {

    }

    private void initFragment() {
        if (getIntent().getExtras().getParcelable(Constants.Extras.ARTICLE) != null) {
            ArticleDetailsFragment articleDetailsFragment = new ArticleDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.Extras.DETAILS, (Article) getIntent().getExtras().getParcelable(Constants.Extras.ARTICLE));
            articleDetailsFragment.setArguments(bundle);
            replaceFragment(R.id.fragment_container, articleDetailsFragment, Constants.FragmentTags.ARTICLE_DETAILS_FRAGMENT_TAG);
        }
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
