package com.jevo.alexander.lesson1.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.adapters.MyCityNameRecyclerViewAdapter;
import com.jevo.alexander.lesson1.model.CityModelImpl;
import com.jevo.alexander.lesson1.model.api.WeatherService;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.presenter.CityPresenter;
import com.jevo.alexander.lesson1.presenter.CityPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityItemFragment extends MvpLceFragment<RecyclerView, OneCity, CityView, CityPresenter>
        implements CityView, SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    View view;
    List<OneCity> weatherCity = new ArrayList<>();

    public CityItemFragment() {
    }


    @Override
    public CityPresenter createPresenter() {
        WeatherService api = new Retrofit
                .Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
        return new CityPresenterImpl(new CityModelImpl(api));
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mColumnCount = this.getResources().getConfiguration().orientation;
        Log.d("COUNT", String.valueOf(mColumnCount));
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setData(OneCity data) {
        Log.d("ГОРОД", data.getName());
        weatherCity.add(data);
        final AssetManager assetManager = getActivity().getAssets();
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        MyCityNameRecyclerViewAdapter listAdapter =
                new MyCityNameRecyclerViewAdapter(weatherCity, mListener, assetManager);
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInfo();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(OneCity item);
    }

}
