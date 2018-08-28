package com.example.aorl.viewpagercolortransition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.aorl.viewpagercolortransition.fragments.Fragment1;

public class MainActivity extends AppCompatActivity implements Fragment1.OnScrollRecyclerViewListener {

  ViewPager viewPager;
  PagerAdapter adapter;
  int marginSides;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.main_app_toolbar);
    setSupportActionBar(toolbar);

    adapter = new PagerAdapter(getSupportFragmentManager(), this);
    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(adapter);

    marginSides = (int) getResources().getDimension(R.dimen.margin_small);
    viewPager.setClipToPadding(false);
    viewPager.setPadding(marginSides, marginSides, marginSides, marginSides);

    int margin = (int) getResources().getDimension(R.dimen.margin_smaller);
    viewPager.setPageMargin(margin);

    viewPager.addOnPageChangeListener(new PagerListener(
        this,
        getWindow(),
        getSupportActionBar(),
        findViewById(R.id.main_container),
        adapter
    ));
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0 && positionOffset == 0) {
   //       viewPager.setPadding(0, 0, 0, 0);
        } else {
   //       viewPager.setPadding(marginSides, marginSides, marginSides, marginSides);
        }
      }

      @Override
      public void onPageSelected(int position) {

      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  @Override
  public void onScroll(boolean up) {
    if (viewPager.getCurrentItem() == 0){
      int padding = viewPager.getPageMargin();
      if (!up && padding != 0) {
        viewPager.setPadding(0, 0, 0, 0);
      } else if (padding != marginSides) {
        viewPager.setPadding(marginSides, marginSides, marginSides, marginSides);
      }
    }
  }
}
