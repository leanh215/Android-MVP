package vn.nano.androidmvp.ui.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.List;

import butterknife.ButterKnife;
import vn.nano.androidmvp.R;
import vn.nano.androidmvp.data.model.Repository;
import vn.nano.androidmvp.data.model.User;
import vn.nano.androidmvp.databinding.FragmentUserDetailsBinding;
import vn.nano.androidmvp.ui.adapter.RepositoryAdapter;
import vn.nano.core_library.mvp.BaseTiFragment;

/**
 * Created by alex on 9/4/17.
 */

public class UserDetailsFragment extends BaseTiFragment<UserDetailsPresenter, UserDetailsView>
    implements UserDetailsView{

    private static final String KEY_USER = "user";

    public static UserDetailsFragment getInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_USER, new Gson().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public UserDetailsPresenter providePresenter() {
        UserDetailsPresenter presenter = new UserDetailsPresenter();
        User user = new Gson().fromJson(getArguments().getString(KEY_USER), User.class);
        presenter.setUser(user);
        return presenter;
    }

    FragmentUserDetailsBinding mBinding;
    RepositoryAdapter repositoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        getPresenter().getRepositories(getPresenter().getUser().getLogin());
    }

    private void initUI() {
        mBinding.getRoot().setOnClickListener(null);
        mBinding.swipeLayout.setOnRefreshListener(() -> getPresenter().getRepositories(getPresenter().getUser().getLogin()));

        mBinding.rvRepository.setLayoutManager(new LinearLayoutManager(getActivity()));
        repositoryAdapter = new RepositoryAdapter();
        mBinding.rvRepository.setAdapter(repositoryAdapter);
    }

    // --------- OVERRIDE DEFAULTS ----------
    // --------- OVERRIDE DEFAULTS ----------
    // --------- OVERRIDE DEFAULTS ----------

    @Override
    public void showLoading(boolean cancelable) {
        mBinding.swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mBinding.swipeLayout.setRefreshing(false);
    }

    // --------- API CALLBACK ----------
    // --------- API CALLBACK ----------
    // --------- API CALLBACK ----------

    @Override
    public void displayRepositories(List<Repository> repositories) {
        repositoryAdapter.setRepositories(repositories);
    }
}
