package vn.nano.androidmvp.ui.user;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vn.nano.androidmvp.MyApplication;
import vn.nano.androidmvp.data.model.User;
import vn.nano.androidmvp.data.remote.GitHubService;
import vn.nano.core_library.mvp.BaseTiPresenter;

/**
 * Created by alex on 9/4/17.
 */

public class UserDetailsPresenter extends BaseTiPresenter<UserDetailsView> {

    @Inject
    GitHubService gitHubService;

    private User user;

    public UserDetailsPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void getRepositories(String username) {
        gitHubService
                .getRepositories(username)
                .doOnSubscribe(disposable -> sendToView(view -> view.showLoading(true)))
                .doAfterTerminate(() -> sendToView(view -> view.hideLoading()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                    sendToView(view -> view.displayRepositories(repositories));
                }, throwable -> {
                    sendToView(view -> view.showError(throwable, GitHubService.ApiError.class));
                });
    }


}
