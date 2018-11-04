package com.example.aorl.viewpagercolortransition;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.example.aorl.viewpagercolortransition.fragments.Fragment1;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, Fragment1.OnScrollRecyclerViewListener {

  public static final int ANIM_DURATION = 100;
  private ViewPager viewPager;
  private int marginSides;
  private int paddingList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initUI();

    setListeners();
  }

  private void initUI() {
    loadToolbar();

    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(adapter);

    marginSides = (int) getResources().getDimension(R.dimen.margin_smaller);
    paddingList = marginSides;
    viewPager.setClipToPadding(false);
    setViewPagerPadding(marginSides);

    int margin = (int) getResources().getDimension(R.dimen.margin_smallest);
    viewPager.setPageMargin(margin);

    TabLayout tabLayout = findViewById(R.id.tab_layout);
    tabLayout.setupWithViewPager(viewPager, true);
  }

  private void loadToolbar() {
    Toolbar toolbar = findViewById(R.id.main_app_toolbar);
    setSupportActionBar(toolbar);
  }

  private void setListeners() {
    viewPager.addOnPageChangeListener(this);
  }

  @Override
  public void onScroll(int dy) {
    if (viewPager.getCurrentItem() == 0) {
      int padding = paddingList - dy;
      padding = Math.max(padding, 0);
      padding = Math.min(padding, marginSides);
      paddingList = padding;
      setViewPagerPadding(paddingList);
    }
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if (position == 0 && positionOffset == 0) {
      setAnimViewPagerPadding(marginSides, paddingList);
    } else {
      setViewPagerPadding(marginSides);
    }
  }

  @Override
  public void onPageSelected(int position) {}

  @Override
  public void onPageScrollStateChanged(int state) {}

  private void setViewPagerPadding(int padding) {
    viewPager.setPadding(padding, 0, padding, padding*2);
    viewPager.setPageTransformer(true, new PageTransformer());
  }

  private void setAnimViewPagerPadding(int from, int to) {
    ValueAnimator anim = ValueAnimator.ofInt(from, to);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int val = (Integer) valueAnimator.getAnimatedValue();
        setViewPagerPadding(val);
      }
    });
    anim.setDuration(ANIM_DURATION);
    anim.start();
  }
}
