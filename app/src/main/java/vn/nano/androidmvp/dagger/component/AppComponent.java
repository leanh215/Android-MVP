package vn.nano.androidmvp.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import vn.nano.androidmvp.dagger.module.AppModule;
import vn.nano.androidmvp.ui.user.SearchUserPresenter;
import vn.nano.androidmvp.ui.user.UserDetailsPresenter;

/**
 * Created by alex on 9/4/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(SearchUserPresenter presenter);

    void inject(UserDetailsPresenter presenter);

}
