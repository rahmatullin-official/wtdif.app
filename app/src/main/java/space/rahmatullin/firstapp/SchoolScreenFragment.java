package space.rahmatullin.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SchoolScreenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View schoolScreenView = inflater.inflate(R.layout.fragment_school_screen, container, false);
//
//        Button buttonNext = (Button)schoolScreenView.findViewById(R.id.button_next);
//        Button buttonPrev = (Button)schoolScreenView.findViewById(R.id.button_prev);
//
//        buttonNext.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.schoolScreenFragment, R.id.homeScreenFragment));
//        buttonPrev.setOnClickListener(view -> ((MainActivity)getActivity()).changeFragment(R.id.schoolScreenFragment, R.id.planeScreenFragment));

        return schoolScreenView;
    }
}