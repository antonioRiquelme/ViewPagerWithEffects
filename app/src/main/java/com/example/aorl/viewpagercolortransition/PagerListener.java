package com.example.aorl.viewpagercolortransition;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

public class PagerListener implements ViewPager.OnPageChangeListener {

  private final Integer[] bgColors;
  @Nullable
  private final ActionBar supportActionBar;
  private final Window window;
  private final View container;
  private final int totalPages;
  private final PagerAdapter adapter;
  private final Map<Integer, Integer[]> colorsPages;

  public PagerListener(
      Context context,
      Window window,
      @Nullable ActionBar supportActionBar,
      View container,
      PagerAdapter adapter) {
    this.supportActionBar = supportActionBar;
    this.container = container;
    this.totalPages = adapter.getCount();
    this.window = window;
    this.adapter = adapter;

    bgColors = new Integer[]{
        ContextCompat.getColor(context, R.color.transactions_list_background),
        ContextCompat.getColor(context, R.color.secondary_color),
        ContextCompat.getColor(context, R.color.orange)
    };

    colorsPages = new HashMap<>(totalPages);
    colorsPages.put(0, new Integer[]{
        ContextCompat.getColor(context, R.color.yellow),
        ContextCompat.getColor(context, R.color.bgFragment1),
    });
    colorsPages.put(1, new Integer[]{
        ContextCompat.getColor(context, R.color.green),
        ContextCompat.getColor(context, R.color.bgFragment2),
    });
    colorsPages.put(2, new Integer[]{
        ContextCompat.getColor(context, R.color.blue),
        ContextCompat.getColor(context, R.color.bgFragment3),
    });
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if (position < (totalPages - 1) && position < (bgColors.length - 1)) {
      Integer backgroundColor =
          (Integer)
              new ArgbEvaluator()
                  .evaluate(positionOffset, bgColors[position], bgColors[position + 1]);
      changeUIColor(backgroundColor);
    } else {
      changeUIColor(bgColors[bgColors.length - 1]);

    }

    Integer[] bgColorsPage = colorsPages.get(position);
    changeBgPageColor(position, (Integer)
        new ArgbEvaluator()
            .evaluate(positionOffset, bgColorsPage[0], bgColorsPage[1]));
  }

  @Override
  public void onPageSelected(int position) {
    if (supportActionBar != null) {
      supportActionBar.setTitle("Fragment " + (position + 1));
    }
  }

  @Override
  public void onPageScrollStateChanged(int state) {
  }

  private void changeUIColor(@ColorInt int color) {
    container.setBackground(new ColorDrawable(color));
    window.setStatusBarColor(color);
  }

  private void changeBgPageColor(int position, @ColorInt int color) {
    View view = adapter.getItem(position).getView();
    if (view != null) {
      view.setBackgroundColor(color);
    }
  }
}
