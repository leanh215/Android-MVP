package vn.nano.androidmvp.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.nano.androidmvp.data.model.Repository;
import vn.nano.androidmvp.data.model.SearchResult;
import vn.nano.core_library.utils.ApiErrorUtils;

/**
 * Created by alex on 9/4/17.
 */

public interface GitHubService {

    String END_POINT = "https://api.github.com/";

    @GET("search/users")
    Observable<SearchResult> searchUser(@Query("q") String username);

    @GET("users/{user_name}/repos")
    Observable<List<Repository>> getRepositories(@Path("user_name") String username);

    class Factory {
        public static GitHubService create() {

            OkHttpClient client = new OkHttpClient.Builder()
                                    .addInterceptor(new StethoInterceptor())
                                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(END_POINT)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .client(client)
                                    .build();

            return retrofit.create(GitHubService.class);
        }
    }

    class ApiError extends ApiErrorUtils.ApiError{

        @SerializedName("message")
        @Expose
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        protected String getErrorMessage() {
            return message;
        }
    }
}
