package space.rahmatullin.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import space.rahmatullin.firstapp.ChooseSexFragmentArgs;

public class ChooseSexFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View chooseSexView = inflater.inflate(R.layout.fragment_choose_sex, container, false);

        Button buttonMale = (Button) chooseSexView.findViewById(R.id.button_male);
        Button buttonFemale = (Button) chooseSexView.findViewById(R.id.button_female);
        TextView text = (TextView)chooseSexView.findViewById(R.id.textView2);

        ChooseSexFragmentArgs chooseSexFragmentArgs = ChooseSexFragmentArgs.fromBundle(getArguments());
        Integer id = chooseSexFragmentArgs.getId();
        text.setText(id + "");


        return chooseSexView;
    }

    public void changeNavFragment(int navLink, String sex) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }
}