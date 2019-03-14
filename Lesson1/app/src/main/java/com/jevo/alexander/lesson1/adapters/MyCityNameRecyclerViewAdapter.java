package com.jevo.alexander.lesson1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.tools.Constants;
import com.jevo.alexander.lesson1.tools.ItemConvert;
import com.jevo.alexander.lesson1.view.city.CityItemFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


@SuppressWarnings("ALL")
public class MyCityNameRecyclerViewAdapter
        extends RecyclerView.Adapter<MyCityNameRecyclerViewAdapter.ViewHolder> {

    private final List<OneCity> values;
    private final OnListFragmentInteractionListener listener;
    public static int lastVisibleItem;
    public static int totalItemCount;

    /**
     * @param items
     * @param listener
     * @param assetManager
     */
    public MyCityNameRecyclerViewAdapter(
            List<OneCity> items, OnListFragmentInteractionListener listener) {
        values = items;
        this.listener = listener;
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
        OneCity item = values.get(position);
        holder.contentDesc.setText(item.getWeather().get(0).getDescription());
        holder.contentView.setText(item.getName());
        holder.contentTemp.setText(ItemConvert.getTempString(holder.view, item));
        ItemConvert.getTempImage(holder.view, item);
        holder.view.setOnClickListener(
                (View v) -> {
                    if (null != listener) {
                        listener.onListFragmentInteraction(item);
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
