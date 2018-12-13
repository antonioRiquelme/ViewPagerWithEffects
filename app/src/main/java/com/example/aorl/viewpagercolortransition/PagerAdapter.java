package com.example.aorl.viewpagercolortransition;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.aorl.viewpagercolortransition.fragments.Fragment1;
import com.example.aorl.viewpagercolortransition.fragments.Fragment2;
import com.example.aorl.viewpagercolortransition.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> pages;

  public PagerAdapter(FragmentManager fm) {
    super(fm);
    pages = new ArrayList<>(3);
    pages.add(Fragment1.newInstance());
    pages.add(Fragment2.newInstance());
    pages.add(Fragment3.newInstance());
  }

  @Override
  public Fragment getItem(int position) {
    return pages.get(position);
  }

  @Override
  public int getCount() {
    return pages.size();
  }
}
