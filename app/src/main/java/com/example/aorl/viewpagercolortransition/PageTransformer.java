package com.example.aorl.viewpagercolortransition;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;

public class PageTransformer implements ViewPager.PageTransformer {

  private final SparseArray<Integer[]> colorsPages;

  public PageTransformer(Context context) {
    colorsPages = getBgColors(context);
  }

  private SparseArray<Integer[]> getBgColors(Context context) {
    SparseArray<Integer[]> colorsPages;
    colorsPages = new SparseArray<>();
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
    return colorsPages;
  }


  @Override
  public void transformPage(@NonNull View page, float position) {
    Integer[] bgColorsPage = null;
    switch (page.getId()) {
      case R.id.container_fragment_1:
        bgColorsPage = colorsPages.get(0);
        break;
      case R.id.container_fragment_2:
        bgColorsPage = colorsPages.get(1);
        break;
      case R.id.container_fragment_3:
        bgColorsPage = colorsPages.get(2);
        break;
      default:
        break;
    }

    if (bgColorsPage != null) {
      changeBgPageColor(page, (Integer)
          new ArgbEvaluator()
              .evaluate(Math.abs(position), bgColorsPage[0], bgColorsPage[1]));
    }
  }

  private void changeBgPageColor(View view, @ColorInt int color) {
    if (view != null) {
      view.setBackgroundColor(color);
    }
  }
}
