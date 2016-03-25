package com.well.swipe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.well.swipe.ItemApplication;
import com.well.swipe.R;
import com.well.swipe.utils.FastBitmapDrawable;

import java.util.ArrayList;

/**
 * Created by mingwei on 3/21/16.
 */
public class AppsIndexView extends LinearLayout {

    private TextView mKey;

    private GridLayout mAppsGridLayout;

    private SwipeEditFavoriteDialog mSwipeEditFavoriteDialog;

    private int mSize;

    private CompoundButton.OnCheckedChangeListener mCheckListener;

    public AppsIndexView(Context context) {
        this(context, null);
    }

    public AppsIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppsIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSize = getResources().getDimensionPixelSize(R.dimen.angleitem_size);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mKey = (TextView) findViewById(R.id.apps_index_item_key);
        mAppsGridLayout = new GridLayout(getContext());
        mAppsGridLayout.setColumnCount(3);
        addView(mAppsGridLayout);
    }

    public void setKeyString(String key) {
        mKey.setText(key);
    }

    public void setGridBackground(int c) {
        mAppsGridLayout.setBackgroundColor(c);
    }

    public void setSwipeEditLayout(SwipeEditFavoriteDialog swipeEditFavoriteDialog) {
        mSwipeEditFavoriteDialog = swipeEditFavoriteDialog;
    }

    public void setContent(ArrayList<ItemApplication> infos, ArrayList<ItemApplication> headerlist) {

        mAppsGridLayout.removeAllViews();
        GridLayoutItemView itemview;
        for (int i = 0; i < infos.size(); i++) {
            itemview = (GridLayoutItemView) LayoutInflater.from(getContext()).inflate(R.layout.
                    gridlayout_item_layout, null);
            itemview.setItemIcon(new FastBitmapDrawable(infos.get(i).mIconBitmap));
            if (headerlist != null) {
                if (containApp(headerlist, infos.get(i))) {
                    itemview.setChecked(true);

                } else {
                    itemview.setChecked(false);
                }
            }
            itemview.setTag(infos.get(i));
            itemview.setItemTitle(infos.get(i).mTitle.toString());
            itemview.setOnClickListener(mSwipeEditFavoriteDialog);
            mAppsGridLayout.addView(itemview, Math.min(1, mAppsGridLayout.getChildCount()), new
                    LayoutParams(mSize, mSize));
        }
    }

    public boolean containApp(ArrayList<ItemApplication> applist, ItemApplication app) {
        for (int i = 0; i < applist.size(); i++) {

            if (applist.get(i).mIntent.getComponent().getClassName().equals(app.mIntent.getComponent()
                    .getClassName())) {

                return true;
            }
        }
        return false;
    }


}
