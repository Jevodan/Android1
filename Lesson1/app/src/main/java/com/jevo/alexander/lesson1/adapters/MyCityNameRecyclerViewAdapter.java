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
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.ItemConvert;
import com.jevo.alexander.lesson1.view.CityItemFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

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

    public static final int FARENGEIT = 273;
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

        holder.contentDesc.setText(values.get(position).getWeather().get(0).getDescription());
        holder.contentView.setText(values.get(position).getName());
        holder.contentTemp.setText(ItemConvert.getTempString(holder.view, values.get(position)));
        ItemConvert.getTempImage(holder.view, values.get(position));
        holder.view.setOnClickListener(
                (View v) -> {
                    if (null != listener) {
                        listener.onListFragmentInteraction(values.get(position));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    private void setImage(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(Constants.iconWeatherExternalLink + values.get(position).getWeather().get(0).getIcon() + ".png")
                .placeholder(R.drawable.cloudy)
                .error(R.drawable.cloudy)
                .into(holder.idView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView contentView;
        final TextView contentTemp;
        final TextView contentDesc;
        ImageView idView;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            idView = view.findViewById(R.id.imageView);
            contentView = view.findViewById(R.id.titleView);
            contentTemp = view.findViewById(R.id.tempTextView);
            contentDesc = view.findViewById(R.id.descTextView);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
