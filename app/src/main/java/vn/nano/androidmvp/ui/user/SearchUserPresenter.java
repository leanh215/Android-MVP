package vn.nano.androidmvp.ui.user;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vn.nano.androidmvp.MyApplication;
import vn.nano.androidmvp.data.remote.GitHubService;
import vn.nano.core_library.mvp.BaseTiPresenter;

/**
 * Created by alex on 9/4/17.
 */

public class SearchUserPresenter extends BaseTiPresenter<SearchUserView> {

    @Inject
    GitHubService gitHubService;

    public SearchUserPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void searchUser(String username) {
        gitHubService.searchUser(username)
                .doOnSubscribe(disposable -> sendToView(view -> view.showLoading(true)))
                .doAfterTerminate(() -> sendToView(view -> view.hideLoading()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResult -> {
                    sendToView(view -> {
                        view.displayUsers(searchResult.getUsers());
                    });
                }, throwable -> {
                    sendToView(view -> {
                        view.showError(throwable, GitHubService.ApiError.class);
                    });
                });
    }
}
