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

public class PagerAdapter extends FragmentStatePagerAdapter implements ViewPager.PageTransformer {

  private final List<Fragment> pages;
  private Context context;

  public PagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
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

  @Override
  public void transformPage(@NonNull View page, float position) {
    Log.e("PageTransformer", ">>>>>>>>> position: " + position);

    Integer[] colors = new Integer[]{
        ContextCompat.getColor(context,R.color.white),
        page.getSolidColor()
    };

   // page.setBackgroundColor(Math.abs(position) > 0.5 ? colors[0]: colors[1]);
   // page.setBackgroundColor(colors[0]);
  }
}
