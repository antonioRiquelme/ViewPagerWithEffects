package com.example.aorl.viewpagercolortransition;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

public class PageTransformer implements ViewPager.PageTransformer {

  @Override
  public void transformPage(@NonNull View page, float position) {
    CardView cardView = page.findViewById(R.id.overlap_card);
    if (cardView != null) {

      if (position < -1) { // [-Infinity,-1)
        // This page is way off-screen to the left.
        cardView.setAlpha(1f);

      } else if (position <= 1) { // [-1,1]
        // Modify the default slide transition to shrink the page as well
        float scaleFactor = Math.abs(position);
        cardView.setAlpha(scaleFactor);

      } else { // (1,+Infinity]
        // This page is way off-screen to the right.
        cardView.setAlpha(1f);
      }
    }
  }
}
