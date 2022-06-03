package space.rahmatullin.firstapp;

import static space.rahmatullin.firstapp.ChooseSexFragment.checkUserMoney;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import space.rahmatullin.firstapp.HomeScreenFragmentDirections;

public class HomeScreenFragment extends Fragment {
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeScreenView = inflater.inflate(R.layout.fragment_home_screen, container, false);

        Button buttonStart = (Button) homeScreenView.findViewById(R.id.button_start);
        Button buttonPrev = (Button) homeScreenView.findViewById(R.id.button_prev);
        Button buttonNext = (Button) homeScreenView.findViewById(R.id.button_next);
        Button buttonShop = (Button)homeScreenView.findViewById(R.id.button_magazine);

        buttonStart.setOnClickListener(view -> {
            HomeScreenFragmentDirections.ActionHomeScreenFragmentToChooseSexFragment action = HomeScreenFragmentDirections.actionHomeScreenFragmentToChooseSexFragment();
            action.setId(1);
            NavHostFragment.findNavController(this).navigate((NavDirections) action);
        });
        buttonNext.setOnClickListener(view -> changeNavFragment(R.id.action_homeScreenFragment_to_planeScreenFragment));
        buttonPrev.setOnClickListener(view -> changeNavFragment(R.id.action_homeScreenFragment_to_schoolScreenFragment));
        buttonShop.setOnClickListener(view -> createShopDialog());

        return homeScreenView;
    }

    private void createShopDialog(){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.shop_dialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        TextView btnclose = dialog.findViewById(R.id.btnclose);
        Button button_first = dialog.findViewById(R.id.button_first);
        Button button_second = dialog.findViewById(R.id.button_second);
        Button button_third = dialog.findViewById(R.id.button_third);

        btnclose.setOnClickListener(view -> dialog.dismiss());
        button_first.setOnClickListener(view -> checkUserMoney(2, getView()));
        button_second.setOnClickListener(view -> checkUserMoney(3, getView()));
        button_third.setOnClickListener(view -> checkUserMoney(4, getView()));
        dialog.show();
    }



    public void changeNavFragment(int navLink) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }


}