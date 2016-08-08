package com.akoufatzis.weatherapp.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaTestPlugins;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 08/08/16.
 */
public class RxJavaTestRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                RxJavaTestPlugins.resetPlugins();
                RxAndroidPlugins.getInstance().reset();
                RxJavaPlugins.getInstance().registerSchedulersHook(rxJavaSchedulersHook);
                RxAndroidPlugins.getInstance().registerSchedulersHook(rxAndroidSchedulersHook);

                base.evaluate();

                RxJavaTestPlugins.resetPlugins();
                RxAndroidPlugins.getInstance().reset();
            }
        };
    }

    private final RxJavaSchedulersHook rxJavaSchedulersHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    private final RxAndroidSchedulersHook rxAndroidSchedulersHook = new RxAndroidSchedulersHook() {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    };
}
