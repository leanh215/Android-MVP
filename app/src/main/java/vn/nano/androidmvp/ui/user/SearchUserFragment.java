package vn.nano.androidmvp.ui.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.nano.androidmvp.R;
import vn.nano.androidmvp.data.model.User;
import vn.nano.androidmvp.databinding.FragmentSearchUserBinding;
import vn.nano.androidmvp.ui.adapter.UserAdapter;
import vn.nano.core_library.mvp.BaseTiFragment;
import vn.nano.core_library.utils.ViewUtils;

/**
 * Created by alex on 9/4/17.
 */

public class SearchUserFragment extends BaseTiFragment<SearchUserPresenter, SearchUserView>
    implements SearchUserView{

    public static SearchUserFragment getInstance() {
        return new SearchUserFragment();
    }

    @NonNull
    @Override
    public SearchUserPresenter providePresenter() {
        return new SearchUserPresenter();
    }

    FragmentSearchUserBinding mBinding;
    UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_user, container, false);
        ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {

        // swipe to refresh
        mBinding.swipeLayout.setOnRefreshListener(() -> onClickSearch());

        // list user
        mBinding.rvUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        userAdapter = new UserAdapter(user -> openUser(user));
        mBinding.rvUser.setAdapter(userAdapter);
    }

    // ----------- ON CLICK --------- //
    // ----------- ON CLICK --------- //
    // ----------- ON CLICK --------- //

    @OnClick(R.id.btn_search)
    public void onClickSearch() {
        String searchValue = mBinding.etUsername.getText().toString().trim();
        getPresenter().searchUser(searchValue);
        ViewUtils.hideKeyboard(getActivity());
    }

    // ----------- OVERRIDE DEFAULT --------- //
    // ----------- OVERRIDE DEFAULT --------- //
    // ----------- OVERRIDE DEFAULT --------- //

    @Override
    public void showLoading(boolean cancelable) {
        mBinding.swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mBinding.swipeLayout.setRefreshing(false);
    }

    // ----------- OTHER FUNCTIONS --------- //
    // ----------- OTHER FUNCTIONS --------- //
    // ----------- OTHER FUNCTIONS --------- //

    private void openUser(User user) {
        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, UserDetailsFragment.getInstance(user))
                .addToBackStack(null)
                .commit();
    }

    // ----------- API CALLBACK --------- //
    // ----------- API CALLBACK --------- //
    // ----------- API CALLBACK --------- //

    @Override
    public void displayUsers(List<User> users) {
        userAdapter.setUsers(users);
    }
}
