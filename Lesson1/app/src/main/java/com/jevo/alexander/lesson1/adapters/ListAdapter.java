package com.jevo.alexander.lesson1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jevo.alexander.lesson1.CityActivity;
import com.jevo.alexander.lesson1.CityData;
import com.jevo.alexander.lesson1.MainActivity;
import com.jevo.alexander.lesson1.R;

import static com.jevo.alexander.lesson1.CityActivity.CITY;
import static com.jevo.alexander.lesson1.CityActivity.POW;
import static com.jevo.alexander.lesson1.CityActivity.TEMP;
import static com.jevo.alexander.lesson1.CityActivity.WATER;
import static com.jevo.alexander.lesson1.CityActivity.WIND;

/**
 * Created by Alexander on 16.01.2019.
 */
public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_fragment_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        ((ListViewHolder) viewHolder).bindView(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "ТЕСТ222");
                Context context = ((ListViewHolder) viewHolder).mItemText.getContext();
                Intent intent2 = new Intent(context, MainActivity.class);
                intent2.putExtra(CITY, CityData.getTitle()[i]);
                intent2.putExtra(TEMP, CityData.getTemp()[i]);
                intent2.putExtra(POW, CityData.getPow()[i]);
                intent2.putExtra(WATER, CityData.getWater()[i]);
                intent2.putExtra(WIND, CityData.getWind()[i]);
                context.startActivity(intent2);
            }
        });

    }


    @Override
    public int getItemCount() {
        return CityData.getTitle().length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemText;
        private ImageView mItemImage;

        public ListViewHolder(View itemView) {
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.titleView);
            mItemImage = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bindView(int position) {
            mItemText.setText(CityData.title[position]);
            mItemImage.setImageResource(CityData.picturePath[position]);
        }
    }
}
