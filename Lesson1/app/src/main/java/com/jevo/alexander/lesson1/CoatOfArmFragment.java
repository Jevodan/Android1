package com.jevo.alexander.lesson1;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CoatOfArmFragment extends Fragment {

    @BindView(R.id.coat_of_arm_image) ImageView image;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CoatOfArmListener mListener;


    public CoatOfArmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoatOfArmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoatOfArmFragment newInstance(String param1, String param2) {
        CoatOfArmFragment fragment = new CoatOfArmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        Log.d("image", String.valueOf(image));
        image.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.clickOnImage();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CoatOfArmListener) {
            mListener = (CoatOfArmListener) context;
        } else {
            Log.e("ERROR"," must implement CoatOfArmListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void changeImage(int id) {
        Log.d("PICTURE", String.valueOf(id));
        image.setImageResource(id);
    }

    public interface CoatOfArmListener {
        // TODO: Update argument type and name
        void clickOnImage();
    }
}