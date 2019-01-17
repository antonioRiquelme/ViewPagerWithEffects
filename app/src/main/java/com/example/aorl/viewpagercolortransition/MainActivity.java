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
  private int paddingList1;
  private int paddingList2;
  private int paddingList3;

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
    paddingList1 = marginSides;
    paddingList2 = marginSides;
    paddingList3 = marginSides;
    viewPager.setClipToPadding(false);
    setViewPagerPadding(marginSides);

    int margin = (int) getResources().getDimension(R.dimen.margin_smallest);
    viewPager.setPageMargin(margin);

    TabLayout tabLayout = findViewById(R.id.tab_layout);
    tabLayout.setupWithViewPager(viewPager, true);

    PagerListener listener = new PagerListener(
        this,
        getWindow(),
        getSupportActionBar(),
        findViewById(R.id.main_container),
        adapter.getCount());
    viewPager.addOnPageChangeListener(listener);

  }

  private void loadToolbar() {
    Toolbar toolbar = findViewById(R.id.main_app_toolbar);
    setSupportActionBar(toolbar);
  }

  private void setListeners() {
    viewPager.addOnPageChangeListener(this);
    viewPager.setPageTransformer(false, new PageTransformer());
  }

  @Override
  public void onScroll(int dy) {
    if (viewPager.getCurrentItem() == 0) {
      int padding = paddingList1 - dy;
      padding = Math.max(padding, 0);
      padding = Math.min(padding, marginSides);
      paddingList1 = padding;
      setViewPagerPadding(paddingList1);
    } else if (viewPager.getCurrentItem() == 1) {
      int padding = paddingList2 - dy;
      padding = Math.max(padding, 0);
      padding = Math.min(padding, marginSides);
      paddingList2 = padding;
      setViewPagerPadding(paddingList2);
    } else if (viewPager.getCurrentItem() == 2) {
      int padding = paddingList3 - dy;
      padding = Math.max(padding, 0);
      padding = Math.min(padding, marginSides);
      paddingList3 = padding;
      setViewPagerPadding(paddingList3);
    }
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if (position == 0 && positionOffset == 0) {
      setAnimViewPagerPadding(marginSides, paddingList1);
    } else if (position == 1 && positionOffset == 0) {
      setAnimViewPagerPadding(marginSides, paddingList2);
    } else if (position == 2 && positionOffset == 0) {
      setAnimViewPagerPadding(marginSides, paddingList3);
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
