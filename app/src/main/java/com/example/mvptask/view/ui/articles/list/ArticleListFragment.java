package com.example.mvptask.view.ui.articles.list;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.mvptask.R;
import com.example.mvptask.base.BaseFragment;
import com.example.mvptask.data.model.dto.Article;
import com.example.mvptask.helper.Constants;
import com.example.mvptask.helper.Utilities;
import com.example.mvptask.view.ui.articles.details.ArticleDetailsActivity;

import java.util.List;


public class ArticleListFragment extends BaseFragment implements ArticlesContract.View, ArticleClickCallBack {
    private ArticleListAdapter articleListAdapter;
    private ProgressBar progressBar;
    private RecyclerView rlArticle;
    private ArticlesPresenter articlesPresenter;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        initializeViews(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeRecyclerView();
        getArticles();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    protected void initializeViews(View v) {
        rlArticle = (RecyclerView) v.findViewById(R.id.rlArticle);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayArticleList(List<Article> articles) {
        articleListAdapter.setArticleList(articles);
    }

    @Override
    public void showErrorMessage(String message) {
        Utilities.displayToast(message, getActivity());
    }

    @Override
    public void displayFilterArticleList(List<Article> filterList) {
        articleListAdapter.setFilter(filterList);
    }

    @Override
    public void initializeRecyclerView() {
        rlArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListAdapter = new ArticleListAdapter(this, getActivity());
        rlArticle.setAdapter(articleListAdapter);
    }


    @Override
    public void filterArticles(String query) {
        articlesPresenter.onFilterArticles(query);
    }

    @Override
    public void getArticles() {
        articlesPresenter = new ArticlesPresenter(this, getActivity());
        articlesPresenter.getArticles();
    }

    @Override
    public void OnArticleClick(Article article) {
        // navigate to article details
        Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
        intent.putExtra(Constants.Extras.ARTICLE, article);
        startActivity(intent);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        search(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void search(Menu menu) {
        if (null == getActivity())
            return;
        initializeSearchView(menu);
        searchView.setOnQueryTextListener(onQueryTextListener);

    }

    @Override
    public void initializeSearchView(Menu menu) {
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
    }


    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            filterArticles(s);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            filterArticles(s);
            return false;
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
