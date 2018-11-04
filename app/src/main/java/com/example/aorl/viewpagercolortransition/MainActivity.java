package com.example.aorl.viewpagercolortransition;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.aorl.viewpagercolortransition.fragments.Fragment1;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, Fragment1.OnScrollRecyclerViewListener {

  private ViewPager viewPager;
  private PagerAdapter adapter;
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

    adapter = new PagerAdapter(getSupportFragmentManager());
    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(adapter);

    marginSides = (int) getResources().getDimension(R.dimen.margin_smaller);
    paddingList = marginSides;
    viewPager.setClipToPadding(false);
    setViewPagerPaddings(marginSides);

    int margin = (int) getResources().getDimension(R.dimen.margin_smallest);
    viewPager.setPageMargin(margin);

    TabLayout tabLayout = findViewById(R.id.tab_layout);
    tabLayout.setupWithViewPager(viewPager, true);
  }

  private void setViewPagerPaddings(int padding) {
    viewPager.setPadding(padding, 0, padding, padding*2);
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
      setViewPagerPaddings(paddingList);
    }
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if (position == 0 && positionOffset == 0) {
      setViewPagerPaddings(paddingList);
    } else {
      setViewPagerPaddings(marginSides);
    }
  }

  @Override
  public void onPageSelected(int position) {}

  @Override
  public void onPageScrollStateChanged(int state) {}
}
