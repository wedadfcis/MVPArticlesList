package com.example.mvptask.view.ui.details;

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
import com.example.mvptask.common.Utilities;
import com.example.mvptask.data.model.Article;
import com.example.mvptask.view.adapter.ImageAndDateLoader;
import com.example.mvptask.base.BaseFragment;

public class ArticleDetailsFragment extends BaseFragment implements com.example.mvptask.view.ui.details.DetailsContract.View {

    private ImageView imgArticle;
    private TextView txtPublishDate;
    private TextView txtArticleTitle;
    private TextView txtAuthorName;
    private TextView txtDescription;
    private Button btnOpenWebsite;
    private DetailsContract.Presenter detailsContract;

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        detailsContract.onArticleSelected();

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
        imgArticle =  v.findViewById(R.id.article_image);
        txtPublishDate = (TextView) v.findViewById(R.id.publish_date);
        txtArticleTitle = (TextView) v.findViewById(R.id.article_title);
        txtAuthorName = (TextView) v.findViewById(R.id.author_name);
        txtDescription = (TextView) v.findViewById(R.id.description);
        btnOpenWebsite = (Button) v.findViewById(R.id.open_website);
    }

    @Override
    protected void setListeners() {
      btnOpenWebsite.setOnClickListener(openListeners);
    }

    private View.OnClickListener openListeners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            detailsContract.onOpenUrl();
        }
    };

    @Override
    public void onStart(Article article) {
        ImageAndDateLoader.loadImage(imgArticle,article.getUrlToImage());
        ImageAndDateLoader.loadDate(txtPublishDate,article.getPublishedAt());
        txtAuthorName.setText(article.getAuthor());
        txtArticleTitle.setText(article.getTitle());
        txtDescription.setText(article.getDescription());

    }

    @Override
    public void openWebsiteFromUrl(String url) {
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Utilities.displayToast(getActivity().getString(R.string.fail_open_url),getActivity());
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(com.example.mvptask.view.ui.details.DetailsContract.Presenter presenter) {
        detailsContract = presenter;
    }
}
