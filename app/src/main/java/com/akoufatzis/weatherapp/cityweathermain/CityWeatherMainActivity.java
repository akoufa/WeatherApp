package com.akoufatzis.weatherapp.cityweathermain;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.cityweatherfavorite.view.CityWeatherFavoriteFragment;
import com.akoufatzis.weatherapp.cityweathersearch.view.CityWeatherSearchFragment;
import com.akoufatzis.weatherapp.common.BaseToolbarActivity;
import com.akoufatzis.weatherapp.widgets.BottomBarItem;
import com.akoufatzis.weatherapp.widgets.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.akoufatzis.weatherapp.utilities.ViewUtils.dp2px;

/**
 * Created by alexk on 02/05/16.
 */
public class CityWeatherMainActivity extends BaseToolbarActivity implements BottomBarLayout.OnBottomBarItemClickListener {

    private static final String BOTTOM_BAR_TAG = "bottom_bar_tag";

    @BindView(R.id.rootCoordinatorLayout)
    public CoordinatorLayout coordinatorLayout;
    protected BottomBarLayout bottomBarLayout;
    private int currentIndex = -1;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_citiyweathermain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<BottomBarItem> bottomBarItems = new ArrayList<>(3);
        bottomBarItems.add(new BottomBarItem(this,
                new BottomBarItem.BottomBarItemOption(R.drawable.ic_search_white_24dp,
                        R.string.search, android.R.color.white, 0)));
        bottomBarItems.add(new BottomBarItem(this,
                new BottomBarItem.BottomBarItemOption(R.drawable.ic_favorite_white_24dp,
                        R.string.favorites, android.R.color.white, 1)));
        bottomBarItems.add(new BottomBarItem(this,
                new BottomBarItem.BottomBarItemOption(R.drawable.ic_gps_fixed_white_24dp,
                        R.string.nearby, android.R.color.white, 2)));
        bottomBarLayout = new BottomBarLayout(this, bottomBarItems, 0);
        bottomBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.accent));

        CoordinatorLayout.LayoutParams params
                = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(56));
        params.gravity = Gravity.BOTTOM;
        coordinatorLayout.addView(bottomBarLayout, params);

        bottomBarLayout.setOnBottomBarItemClickListener(this);

        //Initial State
        onBottomBarItemClicked(bottomBarItems.get(0));
    }

    @Override
    protected void onDestroy() {
        bottomBarLayout.setOnBottomBarItemClickListener(null);
        super.onDestroy();
    }

    @Override
    public void onBottomBarItemClicked(BottomBarItem bottomBarItem) {
        
        // Already shown
        if (currentIndex == bottomBarItem.getIndex()) {

            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = getFragmentForIndex(bottomBarItem.getIndex());

        fragmentManager.beginTransaction().replace(R.id.bottom_bar_fragment_container,
                fragment, BOTTOM_BAR_TAG).commit();

    }

    private Fragment getFragmentForIndex(int index) {

        // TODO: Remove this when the other cases are implemented
        Fragment fragment = CityWeatherSearchFragment.newInstance();

        switch (index) {

            case 0:
                fragment = CityWeatherSearchFragment.newInstance();
                currentIndex = 0;
                break;

            case 1:
                fragment = CityWeatherFavoriteFragment.newInstance();
                currentIndex = 1;
                break;

            case 2:
                Toast.makeText(this, "This feature is not yet implemented", Toast.LENGTH_SHORT).show();
//                fragment = CityWeatherSearchFragment.newInstance();
                currentIndex = 2;
                break;
        }

        return fragment;
    }
}