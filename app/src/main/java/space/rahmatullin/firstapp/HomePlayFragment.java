package space.rahmatullin.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import space.rahmatullin.firstapp.R;

public class HomePlayFragment extends Fragment {

    Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // диалоговое окно
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        // кнопка закрытия диалогового окна
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_homePlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });

        // кнопка продолжить
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(view -> dialog.dismiss());

        dialog.show(); // показать диалоговое окно

        View homePlayView = inflater.inflate(R.layout.fragment_home_play, container, false);

        HomePlayFragmentArgs homePlayFragmentArgs = HomePlayFragmentArgs.fromBundle(getArguments());

        TextView text = (TextView) homePlayView.findViewById(R.id.coins);

        String Sex = homePlayFragmentArgs.getSex();
        int coins = homePlayFragmentArgs.getCoins();
        int skin = homePlayFragmentArgs.getSkin();
        int points_home = homePlayFragmentArgs.getPointsHome();

        Player myPlayer = new Player(Sex, coins, skin, points_home);

        text.setText("\uD83D\uDCB0 " + myPlayer.coins);
        text.setTextSize(24);

        return homePlayView;

    }

    public void changeNavFragment(int navLink) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }
}