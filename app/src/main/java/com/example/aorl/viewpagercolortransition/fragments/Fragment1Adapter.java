package com.example.aorl.viewpagercolortransition.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aorl.viewpagercolortransition.R;

import java.util.List;

public class Fragment1Adapter extends RecyclerView.Adapter<Fragment1Adapter.ViewHolder> {

  private final List<String> items;

  public Fragment1Adapter(List<String> items) {
    this.items = items;
  }

  @NonNull
  @Override
  public Fragment1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {
    TextView item = holder.item;
    item.setText(items.get(listPosition));
  }

  // Static inner class to initialize the views of rows
  static class ViewHolder extends RecyclerView.ViewHolder {
    TextView item;

    ViewHolder(View itemView) {
      super(itemView);
      item = itemView.findViewById(R.id.title);
    }
  }
}
