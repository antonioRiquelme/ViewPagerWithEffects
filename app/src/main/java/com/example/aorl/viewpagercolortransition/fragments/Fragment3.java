package com.example.aorl.viewpagercolortransition.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aorl.viewpagercolortransition.R;

public class Fragment3 extends Fragment {

  public static Fragment3 newInstance() {
    Bundle args = new Bundle();
    Fragment3 fragment = new Fragment3();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_3, container,false);
  }
}
