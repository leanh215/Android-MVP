package vn.nano.androidmvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.nano.androidmvp.ui.user.SearchUserFragment;
import vn.nano.androidmvp.ui.user.TestFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, SearchUserFragment.getInstance())
                .commit();

    }
}
