package com.jevo.alexander.lesson1.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jevo.alexander.lesson1.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CoatOfArmFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.coat_of_arm_image)
    ImageView image;
    @BindView(R.id.powTextView)
    TextView powTextView;
    @BindView(R.id.waterTextView)
    TextView waterTextView;
    @BindView(R.id.windTextView)
    TextView windTextView;
    @BindView(R.id.tempTextView)
    TextView tempTextView;
    @BindView(R.id.desc_field)
    TextView descTextView;
    @BindView(R.id.city_field)
    TextView cityTextView;

    private CoatOfArmListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        Log.d("image", String.valueOf(image));
        image.setOnClickListener(
                v -> {
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
            Log.e("ERROR", " must implement CoatOfArmListener");
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
