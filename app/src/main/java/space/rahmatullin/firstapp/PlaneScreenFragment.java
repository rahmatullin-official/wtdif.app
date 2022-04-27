package space.rahmatullin.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PlaneScreenFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View planeScreenView =  inflater.inflate(R.layout.fragment_plane_screen, container, false);

        Button buttonNext = (Button)planeScreenView.findViewById(R.id.button_next);
        Button buttonPrev = (Button)planeScreenView.findViewById(R.id.button_prev);

        buttonNext.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.planeScreenFragment, R.id.schoolScreenFragment));
        buttonPrev.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.planeScreenFragment, R.id.homeScreenFragment));

        return planeScreenView;
    }
}