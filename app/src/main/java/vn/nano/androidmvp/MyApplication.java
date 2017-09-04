package vn.nano.androidmvp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

import dagger.internal.DaggerCollections;
import timber.log.Timber;
import vn.nano.androidmvp.dagger.component.AppComponent;
import vn.nano.androidmvp.dagger.component.DaggerAppComponent;
import vn.nano.androidmvp.dagger.module.AppModule;

/**
 * Created by alex on 9/4/17.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private AppComponent mAppComponent;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        // Stetho
        Stetho.initializeWithDefaults(this);

        // Fresco
        Fresco.initialize(this);

        // Timber
        Timber.plant(new Timber.DebugTree());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
