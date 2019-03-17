package com.example.mvptask.view.ui.list;

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
import com.example.mvptask.common.Constants;
import com.example.mvptask.data.model.Article;
import com.example.mvptask.common.Utilities;
import com.example.mvptask.view.adapter.ArticleListAdapter;
import com.example.mvptask.base.BaseFragment;
import com.example.mvptask.view.callback.ArticleClickCallBack;
import com.example.mvptask.view.ui.details.ArticleDetailsActivity;

import java.util.List;


public  class ArticleListFragment extends BaseFragment implements ArticlesContract.View, ArticleClickCallBack {
    private ArticleListAdapter articleListAdapter;
    private ProgressBar proLoad;
    private RecyclerView rlArticle;
    private ArticlesContract.Presenter presenterContract;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        setHasOptionsMenu(true);
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
        startFetchingData();
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
        rlArticle = (RecyclerView) v.findViewById(R.id.article_list);
        proLoad = (ProgressBar) v.findViewById(R.id.loginProgress);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    public void showProgressBar() {
        proLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgressBar() {

        proLoad.setVisibility(View.GONE);
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
    public void onArticleClick(Article article) {
        // navigate to article details
        Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
        intent.putExtra(Constants.Extras.ARTICLE, article);
        startActivity(intent);
    }

    public void initializeRecyclerView() {
        rlArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListAdapter = new ArticleListAdapter(this, getActivity());
        rlArticle.setAdapter(articleListAdapter);
    }


    private void startFetchingData() {

        presenterContract.onFetchArticlesFromServer();
    }

    @Override
    public void onClick(Article article) {

        presenterContract.onItemClick(article);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (null == getActivity())
            return;
        SearchView searchView;
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter  when query submitted
                filterData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter when text is changed
                filterData(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void filterData(String query) {

        presenterContract.onFilterArticles(query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        presenterContract = presenter;
    }
}
