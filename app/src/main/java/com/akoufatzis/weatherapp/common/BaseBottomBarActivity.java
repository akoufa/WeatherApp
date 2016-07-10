package com.akoufatzis.weatherapp.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.cityweatherfavorite.view.CityWeatherFavoriteActivity;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchActivity;
import com.akoufatzis.weatherapp.widgets.BottomBarItem;
import com.akoufatzis.weatherapp.widgets.BottomBarItem.BottomBarItemOption;
import com.akoufatzis.weatherapp.widgets.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

import static com.akoufatzis.weatherapp.utilities.ViewUtils.dp2px;

/**
 * Created by alexk on 03/07/16.
 */
public abstract class BaseBottomBarActivity extends BaseToolbarActivity implements BottomBarLayout.OnBottomBarItemClickListener {

    protected CoordinatorLayout coordinatorLayout;
    protected BottomBarLayout bottomBarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.rootCoordinatorLayout);

        if (coordinatorLayout == null) {

            throw new IllegalArgumentException("When using BaseBottomBarActivity you must provide " +
                    "a CoordinatorLayout as the root layout with id rootCoordinatorLayout");
        }

        List<BottomBarItem> bottomBarItems = new ArrayList<>(3);
        bottomBarItems.add(new BottomBarItem(this, new BottomBarItemOption(R.drawable.ic_search_white_24dp, R.string.search, android.R.color.white, 0)));
        bottomBarItems.add(new BottomBarItem(this, new BottomBarItemOption(R.drawable.ic_favorite_white_24dp, R.string.favorites, android.R.color.white, 1)));
        bottomBarItems.add(new BottomBarItem(this, new BottomBarItemOption(R.drawable.ic_gps_fixed_white_24dp, R.string.nearby, android.R.color.white, 2)));
        bottomBarLayout = new BottomBarLayout(this, bottomBarItems, getBottomBarActivityIndex(this));
        bottomBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.accent));

        CoordinatorLayout.LayoutParams params
                = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(56));
        params.gravity = Gravity.BOTTOM;
        coordinatorLayout.addView(bottomBarLayout, params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomBarLayout.setOnBottomBarItemClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bottomBarLayout.setOnBottomBarItemClickListener(null);
    }

    @Override
    public void onBottomBarItemClicked(BottomBarItem bottomBarItem) {

        if (bottomBarItem.getIndex() != getBottomBarActivityIndex(this)) {

            Intent intent = null;
            switch (bottomBarItem.getIndex()) {

                case 0:
                    intent = new Intent(this, CityWeatherSearchActivity.class);
                    break;

                case 1:

                    intent = new Intent(this, CityWeatherFavoriteActivity.class);
                    break;

                default:

                    // TODO: Implement this
//                intent = new Intent(this, Nearby.class);
                    break;
            }

            if (intent != null) {

                // If already running intent will be delivered to onNewIntent() Method else fresh activity and no back button history
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        }
    }

    private int getBottomBarActivityIndex(Activity currentActivity) {

        if (currentActivity instanceof CityWeatherSearchActivity) {

            return 0;

        } else if (currentActivity instanceof CityWeatherFavoriteActivity) {

            return 1;
        } else {

            return 2;
        }
    }
}
