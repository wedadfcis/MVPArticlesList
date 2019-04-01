package com.example.mvptask.view.ui.articles.details;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvptask.R;
import com.example.mvptask.base.BaseFragment;
import com.example.mvptask.data.model.dto.Article;
import com.example.mvptask.helper.Constants;
import com.example.mvptask.helper.DateLoader;
import com.example.mvptask.helper.ImageLoader;
import com.example.mvptask.helper.Utilities;

public class ArticleDetailsFragment extends BaseFragment implements com.example.mvptask.view.ui.articles.details.DetailsContract.View {

    private ImageView imgArticle;
    private TextView txtPublishDate;
    private TextView txtArticleTitle;
    private TextView txtAuthorName;
    private TextView txtDescription;
    private Button btnOpenWebsite;
    private ArticleDetailsPresenter articleDetailsPresenter;

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            articleDetailsPresenter = new ArticleDetailsPresenter((Article) getArguments().getParcelable(Constants.Extras.DETAILS), this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_details, container, false);
        initializeViews(view);
        setListeners();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        articleDetailsPresenter.loadArticle();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void initializeViews(View v) {
        imgArticle = v.findViewById(R.id.imgArticle);
        txtPublishDate = (TextView) v.findViewById(R.id.txtPublishDate);
        txtArticleTitle = (TextView) v.findViewById(R.id.txtArticleTitle);
        txtAuthorName = (TextView) v.findViewById(R.id.txtAuthorName);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        btnOpenWebsite = (Button) v.findViewById(R.id.btnOpenWebsite);
    }

    @Override
    protected void setListeners() {
        btnOpenWebsite.setOnClickListener(openListeners);
    }

    private View.OnClickListener openListeners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            articleDetailsPresenter.onOpenUrl();
        }
    };

    @Override
    public void updateUI(Article article) {
        if (article.getUrlToImage() != null && !article.getUrlToImage().equals("")) {
            ImageLoader.loadImage(imgArticle, article.getUrlToImage());
        }
        DateLoader.loadDate(txtPublishDate, article.getPublishedAt());
        txtAuthorName.setText(article.getAuthor());
        txtArticleTitle.setText(article.getTitle());
        txtDescription.setText(article.getDescription());

    }

    @Override
    public void openWebsiteFromUrl(String url) {
        if (url != null && !url.equals("")) {
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                Utilities.displayToast(getActivity().getString(R.string.fail_open_url), getActivity());
                e.printStackTrace();
            }
        } else {
            Utilities.displayToast(getActivity().getString(R.string.no_url), getActivity());
        }
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hidProgressBar() {

    }
}
