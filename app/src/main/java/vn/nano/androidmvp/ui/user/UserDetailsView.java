package vn.nano.androidmvp.ui.user;

import java.util.List;

import vn.nano.androidmvp.data.model.Repository;
import vn.nano.core_library.mvp.BaseTiView;

/**
 * Created by alex on 9/4/17.
 */

public interface UserDetailsView extends BaseTiView{

    void displayRepositories(List<Repository> repositories);

}
