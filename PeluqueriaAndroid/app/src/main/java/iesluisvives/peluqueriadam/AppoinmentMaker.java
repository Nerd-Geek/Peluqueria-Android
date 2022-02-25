package iesluisvives.peluqueriadam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppoinmentMaker extends Fragment {

    public AppoinmentMaker() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AppoinmentMaker newInstance(String param1, String param2) {
        AppoinmentMaker fragment = new AppoinmentMaker();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appoinment_maker, container, false);
    }
}