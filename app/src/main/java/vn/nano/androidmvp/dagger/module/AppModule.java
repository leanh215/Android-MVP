package vn.nano.androidmvp.dagger.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import vn.nano.androidmvp.data.remote.GitHubService;

/**
 * Created by alex on 9/4/17.
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    GitHubService provideGitHubService() {
        return GitHubService.Factory.create();
    }

}
