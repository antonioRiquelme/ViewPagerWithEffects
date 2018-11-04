package com.example.aorl.viewpagercolortransition;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aorl.viewpagercolortransition.fragments.Fragment1;

public class ListScrollListener extends RecyclerView.OnScrollListener {

  @Nullable private final Fragment1.OnScrollRecyclerViewListener mListener;
  private final LinearLayoutManager layoutManager;
  private final CardView cardView;
  private final CardView overLapCardView;
  private final float radius;
  private boolean scrolling;

  public ListScrollListener(
      @Nullable Fragment1.OnScrollRecyclerViewListener mListener,
      LinearLayoutManager layoutManager,
      CardView cardView,
      CardView overLapCardView,
      float radius) {
    this.mListener = mListener;
    this.layoutManager = layoutManager;
    this.cardView = cardView;
    this.overLapCardView = overLapCardView;
    this.radius = radius;
  }

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    switch (newState) {
      case RecyclerView.SCROLL_STATE_IDLE:
        System.out.println("The RecyclerView is not scrolling");
        scrolling = false;
        break;
      case RecyclerView.SCROLL_STATE_DRAGGING:
        System.out.println("Scrolling now");
        scrolling = true;
        break;
      case RecyclerView.SCROLL_STATE_SETTLING:
        System.out.println("Scroll Settling");
        break;
    }
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (mListener != null
        && scrolling
        && (dy > 0 || layoutManager.findFirstVisibleItemPosition() == 0)) {
      mListener.onScroll(dy);

      float radius = cardView.getRadius() - dy;
      radius = Math.max(radius, 0);
      radius = Math.min(radius, this.radius);
      cardView.setRadius(radius);
      overLapCardView.setRadius(radius);
    }
  }

}
