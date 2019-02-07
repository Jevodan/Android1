package com.jevo.alexander.lesson1.adapters;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.CityItemFragment.OnListFragmentInteractionListener;
import com.jevo.alexander.lesson1.model.CityRepository;
import com.jevo.alexander.lesson1.model.CityRepository.OneCity;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OneCity} and makes a call to the specified
 * {@link OnListFragmentInteractionListener}. TODO: Replace the implementation with code for your
 * data type.
 */
@SuppressWarnings("ALL")
public class MyCityNameRecyclerViewAdapter
    extends RecyclerView.Adapter<MyCityNameRecyclerViewAdapter.ViewHolder> {

  private final List<OneCity> values;
  private final OnListFragmentInteractionListener listener;
  private final AssetManager assetManager;

  /**
   * @param items
   * @param listener
   * @param assetManager
   */
  public MyCityNameRecyclerViewAdapter(
      List<OneCity> items, OnListFragmentInteractionListener listener, AssetManager assetManager) {
    values = items;
    this.listener = listener;
    this.assetManager = assetManager;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.city_fragment_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    setImage(holder, position);
    holder.contentView.setText(values.get(position).content);
    Log.d("САЙЗ", String.valueOf(values.size()));
    holder.view.setOnClickListener(
        (View v) -> {
          if (null != listener) {
            listener.onListFragmentInteraction(values.get(position));
          }
        });
  }

  private void setImage(@NonNull ViewHolder holder, int position) {
    try {
      InputStream is =
          assetManager.open(
              "weather/"
                  + CityRepository.weatherPicture[CityRepository.getPicturePath()[position]]
                  + ".png");
      Bitmap bitmap = BitmapFactory.decodeStream(is);
      holder.idView.setImageBitmap(bitmap);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getItemCount() {
    return values.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    final View view;
    final TextView contentView;
    ImageView idView;

    ViewHolder(View view) {
      super(view);
      this.view = view;
      idView = view.findViewById(R.id.imageView);
      contentView = view.findViewById(R.id.titleView);
    }

    @NonNull
    @Override
    public String toString() {
      return super.toString() + " '" + contentView.getText() + "'";
    }
  }
}
