package net.vishnu.news.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.vishnu.news.R;
import net.vishnu.news.adapter.NewsRecyclerAdapter;
import net.vishnu.news.httpapi.ApiInterface;
import net.vishnu.news.httpapi.model.NewsData;
import net.vishnu.news.httpapi.model.NewsResponse;
import net.vishnu.news.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPagerFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    String category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString("category");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pager, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        setRecyclerLayoutManager();
        CallNewsTopHeadlineApi();

        return view;
    }

    private void CallNewsTopHeadlineApi() {
        ApiInterface apiService = Utils.getApiInterface();
        Call<NewsResponse> call = apiService.getTopHeadlineNewsResponse("in", category, "9757e8cc02d045bc98a9021c80e147fd");
        call.enqueue(NewsTopHeadlineCallback());
    }

    private void setRecyclerLayoutManager() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setRecyclerListAdapter(List<NewsData> newsDataList) {
        adapter = new NewsRecyclerAdapter(newsDataList, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private Callback<NewsResponse> NewsTopHeadlineCallback() {
        Callback<NewsResponse> callback = new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                List<NewsData> newsDataList = response.body().getArticles();
                setRecyclerListAdapter(newsDataList);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("Failed to fetch data", t.getMessage());
            }
        };

        return callback;
    }
}
