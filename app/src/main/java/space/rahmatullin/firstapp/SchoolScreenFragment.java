package space.rahmatullin.firstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

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
        View homeScreenView = inflater.inflate(R.layout.fragment_school_screen, container, false);

        Button buttonStart = (Button) homeScreenView.findViewById(R.id.button_start_school);
        Button buttonPrev = (Button) homeScreenView.findViewById(R.id.button_prev);
        Button buttonNext = (Button) homeScreenView.findViewById(R.id.button_next);

        buttonStart.setOnClickListener(view -> {
            SchoolScreenFragmentDirections.ActionSchoolScreenFragmentToChooseSexFragment action = SchoolScreenFragmentDirections.actionSchoolScreenFragmentToChooseSexFragment();
            action.setId(3);
            NavHostFragment.findNavController(this).navigate(action);
        });
        buttonNext.setOnClickListener(view -> changeNavFragment(R.id.action_schoolScreenFragment_to_homeScreenFragment));
        buttonPrev.setOnClickListener(view -> changeNavFragment(R.id.action_schoolScreenFragment_to_planeScreenFragment));

        return homeScreenView;
    }

    public void changeNavFragment(int navLink) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }


}
