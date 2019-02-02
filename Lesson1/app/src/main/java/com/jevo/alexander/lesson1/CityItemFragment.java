package com.jevo.alexander.lesson1;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jevo.alexander.lesson1.adapters.MyCityNameRecyclerViewAdapter;
import com.jevo.alexander.lesson1.model.CityRepository;


public class CityItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    View view;

    public CityItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mColumnCount = this.getResources().getConfiguration().orientation;
        Log.d("COUNT", String.valueOf(mColumnCount));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment_item_list, container, false);
        final AssetManager assetManager = getActivity().getAssets();
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        MyCityNameRecyclerViewAdapter listAdapter = new MyCityNameRecyclerViewAdapter(CityRepository.ITEMS, mListener, assetManager);
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        return view;
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

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CityRepository.OneCity item);
    }

}
