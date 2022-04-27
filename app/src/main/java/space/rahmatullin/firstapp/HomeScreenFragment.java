package space.rahmatullin.firstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeScreenFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeScreenView = inflater.inflate(R.layout.fragment_home_screen, container, false);

        Button buttonPrev = (Button)homeScreenView.findViewById(R.id.button_prev);
        Button buttonNext = (Button)homeScreenView.findViewById(R.id.button_next);
        Button buttonStart = (Button)homeScreenView.findViewById(R.id.button_start);



//      buttonStart.setOnClickListener(view -> {});
        buttonNext.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.homeScreenFragment, R.id.planeScreenFragment));
        buttonPrev.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.homeScreenFragment, R.id.schoolScreenFragment));

        return homeScreenView;
    }


}