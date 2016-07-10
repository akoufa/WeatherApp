package com.akoufatzis.weatherapp.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.akoufatzis.weatherapp.utilities.ViewUtils;

import java.util.List;


/**
 * Created by alexk on 03/07/16.
 */
public class BottomBarLayout extends LinearLayout implements View.OnClickListener {

    private List<BottomBarItem> bottomBarItems;
    private OnBottomBarItemClickListener listener;

    public interface OnBottomBarItemClickListener {

        void onBottomBarItemClicked(BottomBarItem item);
    }

    public BottomBarLayout(Context context, List<BottomBarItem> bottomBarItems, int selectedIndex) {
        super(context);
        setBottomBarItems(bottomBarItems, selectedIndex);
        init(context);
    }

    public BottomBarLayout(Context context) {
        super(context);
        init(context);
    }

    public BottomBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

        setOrientation(HORIZONTAL);
    }

    public void setOnBottomBarItemClickListener(OnBottomBarItemClickListener listener) {

        this.listener = listener;
    }

    public void setBottomBarItems(List<BottomBarItem> bottomBarItems, int selectedIndex) {

        if (bottomBarItems.size() < 3 || bottomBarItems.size() > 5) {

            throw new IllegalArgumentException("Material Design Spec defines only bottom bar with 3 to 5 items");
        }

        this.bottomBarItems = bottomBarItems;

        for (int i = 0; i < bottomBarItems.size(); i++) {

            BottomBarItem bottomBarItem = bottomBarItems.get(i);

            // Listening to all the bottombar items
            bottomBarItem.setOnClickListener(this);

            // Set the first item as selected
            if (i == selectedIndex) {

                bottomBarItem.setSelected(true);
            } else {

                bottomBarItem.setSelected(false);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewUtils.dp2px(56));
            params.weight = 1;
            addView(bottomBarItem, params);
        }
    }

    public void setSelectedState(List<BottomBarItem> bottomBarItems, int selectedIndex) {

        for (BottomBarItem bottomBarItem : bottomBarItems) {

            bottomBarItem.setSelected(false);
        }

        BottomBarItem bottomBarItem = bottomBarItems.get(selectedIndex);
        bottomBarItem.setSelected(true);
    }

    @Override
    public void onClick(View v) {

        if (listener != null) {

            BottomBarItem bottomBarItem = (BottomBarItem) v;
//            setSelectedState(bottomBarItems, bottomBarItem.getIndex());
            // Passing the onClick further to our listener
            listener.onBottomBarItemClicked(bottomBarItem);
        }
    }
}
