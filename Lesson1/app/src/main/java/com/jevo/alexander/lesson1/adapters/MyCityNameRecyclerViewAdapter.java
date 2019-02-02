package com.jevo.alexander.lesson1.adapters;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
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





/**
 * {@link RecyclerView.Adapter} that can display a {@link OneCity} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
@SuppressWarnings("ALL")
public class MyCityNameRecyclerViewAdapter extends RecyclerView.Adapter<MyCityNameRecyclerViewAdapter.ViewHolder> {

    private final List<OneCity> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final AssetManager mAssetManager;

    public MyCityNameRecyclerViewAdapter(List<OneCity> items, OnListFragmentInteractionListener listener, AssetManager assetManager) {
        mValues = items;
        mListener = listener;
        mAssetManager = assetManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        try {
            InputStream is = mAssetManager.open("weather/" +
                    CityRepository.weatherPicture[CityRepository.getPicturePath()[position]] + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.mIdView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // holder.mIdView.setImageResource(CityRepository.getPicturePath()[position]);
        holder.mContentView.setText(mValues.get(position).content);
        Log.d("САЙЗ", String.valueOf(mValues.size()));
        holder.mView.setOnClickListener((View v) -> {
            if (null != mListener) {
                Log.d("ТЕСТ", String.valueOf(position));
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount()
    {
               return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        ImageView mIdView;
        final TextView mContentView;
        OneCity mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.imageView);
            mContentView = view.findViewById(R.id.titleView);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
