package iesluisvives.peluqueriadam;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FragmentRegisterUser extends Fragment {

    private Button registerbutton;
    private FragmentContainerView fragmentContainerView;

    public FragmentRegisterUser() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void initcomponents(View view) {
        registerbutton = view.findViewById(R.id.registerButton_id);


    }
    public  Button fedemetodo(){

        return registerbutton;
    }


    private void initlisteners(View viewarriba) {
       registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewabajo) {
                Toast.makeText(getContext(),"dios",Toast.LENGTH_LONG).show();

                viewarriba.setVisibility(View.GONE);


            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register_user, container, false);

        initcomponents(view);
        initlisteners(view);




        return view;
    }


}