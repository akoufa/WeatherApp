package com.akoufatzis.weatherapp.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akoufatzis.weatherapp.R;
import com.akoufatzis.weatherapp.utilities.ViewUtils;

/**
 * Created by alexk on 03/07/16.
 */
public class BottomBarItem extends LinearLayout {

    private TextView bottomBarItemText;
    private ImageView bottomBarItemIcon;
    @DrawableRes
    private int itemIcon;
    @StringRes
    private int itemText;
    @ColorRes
    private int itemTextColor;
    private int index;

    public static class BottomBarItemOption {

        @DrawableRes
        private final int itemIcon;
        @StringRes
        private final int itemText;
        @ColorRes
        public final int itemTextColor;

        public final int index;

        public BottomBarItemOption(int itemIcon, int itemText, int itemTextColor, int index) {
            this.itemIcon = itemIcon;
            this.itemText = itemText;
            this.itemTextColor = itemTextColor;
            this.index = index;
        }
    }

    public BottomBarItem(Context context, BottomBarItemOption bottomBarItemOption) {
        super(context);
        this.itemIcon = bottomBarItemOption.itemIcon;
        this.itemText = bottomBarItemOption.itemText;
        this.itemTextColor = bottomBarItemOption.itemTextColor;
        this.index = bottomBarItemOption.index;
        init(context);
    }

    public BottomBarItem(Context context) {
        super(context);
        init(context);
    }

    public BottomBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomBarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomBarItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottom_bar_item, this, true);
        setOrientation(VERTICAL);
        bottomBarItemIcon = (ImageView) findViewById(R.id.bottom_bar_item_icon);
        bottomBarItemText = (TextView) findViewById(R.id.bottom_bar_item_text);

        bottomBarItemIcon.setImageResource(itemIcon);
        bottomBarItemText.setText(itemText);
        bottomBarItemText.setTextColor(ContextCompat.getColor(context, itemTextColor));

        setClickable(true);
        setFocusable(true);
    }

    public int getIndex() {
        return index;
    }

    public void setSelected(boolean isSelected) {

        if (isSelected) {

            setSelectedState();
        } else {

            setUnselectedState();
        }
    }

    private void setSelectedState() {

        setSelectedPadding();
        setSelectedTextSize(bottomBarItemText);
    }

    private void setUnselectedState() {

        setUnselectedPadding();
        setUnselectedTextSize(bottomBarItemText);
    }

    private void setUnselectedPadding() {

        setPadding(ViewUtils.dp2px(12), ViewUtils.dp2px(8), ViewUtils.dp2px(12), ViewUtils.dp2px(10));
    }

    private void setSelectedPadding() {

        setPadding(ViewUtils.dp2px(12), ViewUtils.dp2px(6), ViewUtils.dp2px(12), ViewUtils.dp2px(10));
    }

    private void setUnselectedTextSize(TextView textView) {

        textView.setTextSize(12);
    }

    private void setSelectedTextSize(TextView textView) {

        textView.setTextSize(14);
    }
}
