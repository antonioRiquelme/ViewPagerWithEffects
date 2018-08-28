package com.example.aorl.viewpagercolortransition.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aorl.viewpagercolortransition.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

  public interface OnScrollRecyclerViewListener {
    void onScroll(boolean up);
  }

  @Nullable
  private OnScrollRecyclerViewListener mListener;

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

    RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
    Fragment1Adapter adapter = new Fragment1Adapter(getItems());

    recyclerView.setAdapter(adapter);

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

      boolean scrolling;

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
        if (mListener != null && scrolling) {
          if (dy > 0) {
            mListener.onScroll(false);
          } else if (dy < 0) {
            mListener.onScroll(true);
          }
        }
      }
    });
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
