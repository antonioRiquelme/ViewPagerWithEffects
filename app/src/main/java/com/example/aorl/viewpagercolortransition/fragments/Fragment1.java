package com.example.aorl.viewpagercolortransition.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aorl.viewpagercolortransition.ListScrollListener;
import com.example.aorl.viewpagercolortransition.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

  public interface OnScrollRecyclerViewListener {
    void onScroll(int dy);
  }

  @Nullable private OnScrollRecyclerViewListener mListener;

  public static Fragment1 newInstance() {
    Bundle args = new Bundle();
    Fragment1 fragment = new Fragment1();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_1, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    CardView cardView = view.findViewById(R.id.cardView);
    float radius = getResources().getDimension(R.dimen.margin_small);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(layoutManager);

    Fragment1Adapter adapter = new Fragment1Adapter(getItems());
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new ListScrollListener(mListener, layoutManager, cardView, radius));
  }

  private List<String> getItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      items.add("Item " + i);
    }
    return items;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnScrollRecyclerViewListener) {
      mListener = (OnScrollRecyclerViewListener) context;
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
}
