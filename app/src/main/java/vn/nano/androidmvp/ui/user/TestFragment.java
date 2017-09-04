package vn.nano.androidmvp.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.nano.androidmvp.R;
import vn.nano.core_library.mvp.BaseFragment;

/**
 * Created by alex on 8/22/17.
 */

public class TestFragment extends BaseFragment {

    public static TestFragment getInstance() {
        return new TestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        getView().findViewById(R.id.btn_test).setOnClickListener(view -> showAlert("This is message"));
    }
}
