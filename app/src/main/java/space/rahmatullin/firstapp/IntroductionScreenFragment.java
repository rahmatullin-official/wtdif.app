package space.rahmatullin.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class IntroductionScreenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View introductionScreenView = inflater.inflate(R.layout.fragment_introduction_screen, container, false);

        Button buttonStartGame = (Button) introductionScreenView.findViewById(R.id.buttonStartGame);

        buttonStartGame.setOnClickListener(view -> {});

        return introductionScreenView;
    }
}