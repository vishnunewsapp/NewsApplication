package net.vishnu.news.httpapi;

import net.vishnu.news.httpapi.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlineNewsResponse(@Query("country") String country, @Query("category") String category,
                                                  @Query("apiKey") String apiKey);
}
