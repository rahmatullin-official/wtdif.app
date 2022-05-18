package space.rahmatullin.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import space.rahmatullin.firstapp.R;

public class HomePlayFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View homePlayView =  inflater.inflate(R.layout.fragment_home_play, container, false);

        HomePlayFragmentArgs homePlayFragmentArgs = HomePlayFragmentArgs.fromBundle(getArguments());

        TextView text = (TextView)homePlayView.findViewById(R.id.mytext);

        String Sex = homePlayFragmentArgs.getSex();
        int coins = homePlayFragmentArgs.getCoins();
        int skin = homePlayFragmentArgs.getSkin();
        int points_home = homePlayFragmentArgs.getPointsHome();

        Player myPlayer = new Player(Sex, coins, skin, points_home);

        text.setText(myPlayer.Name);
        return homePlayView;

    }
}