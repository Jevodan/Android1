package com.jevo.alexander.lesson1.view.city;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.jevo.alexander.lesson1.R;
import com.jevo.alexander.lesson1.adapters.MyCityNameRecyclerViewAdapter;
import com.jevo.alexander.lesson1.model.CityModelImpl;
import com.jevo.alexander.lesson1.model.api.WeatherService;
import com.jevo.alexander.lesson1.model.entity.Cities;
import com.jevo.alexander.lesson1.model.entity.weather.ListCities;
import com.jevo.alexander.lesson1.model.entity.weather.OneCity;
import com.jevo.alexander.lesson1.presenter.ListCityPresenter;
import com.jevo.alexander.lesson1.presenter.ListCityPresenterImpl;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.Completable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.annotation.Nullable;
import static com.jevo.alexander.lesson1.tools.Constants.TOAST_ALL_CITIES_LOAD;
import static com.jevo.alexander.lesson1.tools.Constants.TOAST_NO_CONNECT;

public class CityItemFragment extends MvpLceFragment<RecyclerView, ListCities, CityViewFragment, ListCityPresenter>
        implements CityViewFragment, SwipeRefreshLayout.OnRefreshListener {
    View view;
    List<OneCity> weatherCity = new ArrayList<>();
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private int startPosition = 0;
    private int countItemsLoad = 6;
    private int lastLoadPosition = 0;
    RecyclerView recyclerView;
    MyCityNameRecyclerViewAdapter listAdapter;
    private boolean isLoading = false;
    private Context context;

    @Override
    public ListCityPresenter createPresenter() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        WeatherService api = new Retrofit
                .Builder()
                .baseUrl(WeatherService.baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
        return new ListCityPresenterImpl(new CityModelImpl(api));
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mColumnCount = this.getResources().getConfiguration().orientation;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment_item_list, container, false);
        context = view.getContext();
        return view;
    }

    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isNetworkAvailable()) {
            loadData(false);
        } else {
            weatherCity = getPresenter().readToBase();
            Toast.makeText(getContext(), TOAST_NO_CONNECT, Toast.LENGTH_LONG).show();
        }
        recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listAdapter = new MyCityNameRecyclerViewAdapter(weatherCity, mListener);
        recyclerView.setAdapter(listAdapter);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setData(ListCities data) {
        if (weatherCity.size() < Cities.cities.length) {
            for (OneCity city : data.getList()) {
                weatherCity.add(city);
            }
            final LinearLayoutManager linearLayoutManager
                    = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    startPosition = linearLayoutManager.getItemCount();
                    lastLoadPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && lastLoadPosition + 2 > weatherCity.size() && weatherCity.size() < Cities.cities.length) {
                        loadData(false);
                        isLoading = true;
                        Completable.fromAction(() -> getPresenter().writeToBase(weatherCity)).subscribe();
                    }
                }
            });
            listAdapter.notifyDataSetChanged();
            isLoading = false;
        } else {
            Toast.makeText(getActivity(), TOAST_ALL_CITIES_LOAD, Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInfo(startPosition, countItemsLoad);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(OneCity item);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
        return netWorkInfo != null && netWorkInfo.isConnectedOrConnecting();
    }

}
