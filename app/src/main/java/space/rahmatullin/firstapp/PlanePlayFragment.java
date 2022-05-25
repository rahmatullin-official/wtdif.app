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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PlanePlayFragment extends Fragment {

    private Dialog dialog;
    private Timer timer;
    private int seconds;
    private int minutes = 50;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View planePlayView = inflater.inflate(R.layout.fragment_plane_play, container, false);
        SchoolPlayFragmentArgs schoolPlayFragmentArgs = SchoolPlayFragmentArgs.fromBundle(getArguments());
        TextView text = (TextView) planePlayView.findViewById(R.id.coins);
        ImageView player = (ImageView) planePlayView.findViewById(R.id.player);

        String Sex = schoolPlayFragmentArgs.getSex();
        int coins = schoolPlayFragmentArgs.getCoins();
        int skin = schoolPlayFragmentArgs.getSkin();
        int points_home = schoolPlayFragmentArgs.getPointsSchool();
        Player myPlayer = new Player(Sex, coins, skin, points_home);

        if (myPlayer.skin == 0) {
            player.setImageResource(R.drawable.boy);
        } else if (myPlayer.skin == 1) {
            player.setImageResource(R.drawable.girl);
        }

        List<String> dialogs = Arrays.asList("Привет, Настя! Наконец-то вижу тебя в школе! ", "Привет, " + myPlayer.Name +
                        " я тоже рада тебя видеть! Представляешь, только вышла с больничного и сразу же нужно писать контрольную работу по химии…" +
                        "Вот бы кто-нибудь сорвал урок, было бы здорово! ", "Как же я тебя понимаю, я тоже не готовился к контрольной работе…" +
                        "Только вот контрольную работу мы напишем при любом раскладе, даже если в школе случится пожарная тревога!",
                "Хватит тоску нагонять, пошли лучше тему повторим…");

        text.setText("\uD83D\uDCB0 " + myPlayer.coins);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.school_dialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        // кнопка закрытия диалогового окна
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_schoolPlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });

        // кнопка продолжить
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(view -> {
            dialog.dismiss();
            timerText(planePlayView, 4, dialogs, myPlayer, 0);
        });

        dialog.show(); // показать диалоговое окно


        return planePlayView;
    }

    private void timerText(View schoolPlayView, int pause, List<String> dialogs,
                           Player myPlayer, int btncnt) {

        TextView characterText = (TextView) schoolPlayView.findViewById(R.id.charactertext);
        TextView playerText = (TextView) schoolPlayView.findViewById(R.id.playertext);
        ImageView character = (ImageView) schoolPlayView.findViewById(R.id.character);
        ImageView player = (ImageView) schoolPlayView.findViewById(R.id.player);

        final int[] cnt = {0};
        final int[] buttonsCnt = {btncnt};

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if (seconds == 0) {
                    minutes--;
                    seconds = 60;
                }
                if (cnt[0] == pause) {
                    timer.purge();
                    timer.cancel();
                    if (buttonsCnt[0] == 0) {
                        requireActivity().runOnUiThread(() -> {
                            characterText.setText("");
                            character.setVisibility(View.INVISIBLE);
                        });
                        ImageView background = schoolPlayView.findViewById(R.id.school_background);
                        background.setImageResource(R.drawable.cabinet);
                        ImageView character = schoolPlayView.findViewById(R.id.character);
                        character.setImageResource(R.drawable.gromkogovoritel);
                    }
                    if (buttonsCnt[0] == 2) {
                        requireActivity().runOnUiThread(() -> {
                            characterText.setText("");
                            character.setVisibility(View.INVISIBLE);
                        });
                        ImageView character = schoolPlayView.findViewById(R.id.character);
                        character.setImageResource(R.drawable.teacher);

                    }
                    if (buttonsCnt[0] == 4) {
                        ImageView character = schoolPlayView.findViewById(R.id.character);
                        character.setImageResource(R.drawable.terrorist);
                    }
                    buttonsCnt[0]++;
                    setTextToButtons(schoolPlayView, buttonsCnt[0], myPlayer);
                    System.out.println("done " + buttonsCnt[0]);
                }
                if (seconds % 10 == 0) {
                    requireActivity().runOnUiThread(() -> {
                        if (buttonsCnt[0] == 0) {
                            if (cnt[0] == 0 || cnt[0] == 2 || cnt[0] == 3) {
                                characterText.setText("");
                                character.setVisibility(View.INVISIBLE);
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            } else {
                                playerText.setText("");
                                player.setVisibility(View.INVISIBLE);
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if (buttonsCnt[0] == 1) {
                            if (cnt[0] == 0) {
                                character.setVisibility(View.INVISIBLE);
                                characterText.setText("");
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            } else {
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if (buttonsCnt[0] == 2) { // teacher dialog
                            playerText.setText("");
                            player.setVisibility(View.INVISIBLE);
                            character.setVisibility(View.VISIBLE);
                            characterText.setText(dialogs.get(cnt[0]));
                        }
                        cnt[0]++;
                    });
                    System.out.println(buttonsCnt[0]);
                }
                seconds--;
//                System.out.println(seconds);
            }
        }, 1000, 1000);
    }

    private void setTextToButtons(View schoolPlayView, int cnt, Player myPlayer) {

        Button firstButton = schoolPlayView.findViewById(R.id.firstChoiceButton);
        Button secondButton = schoolPlayView.findViewById(R.id.secondChoiceButton);
        Button thirdButton = schoolPlayView.findViewById(R.id.thirdChoiceButton);

        switch (cnt) {
            case 1:
                List<String> list = Arrays.asList("H-водород O- кислород, я …", "Внимание всем учителям и ученикам! В срочном порядке следует запереться в кабинетах! В здании школы террор…");
                timerText(schoolPlayView, 2, list, myPlayer, cnt);
                break;
            case 2:
                List<String> list2 = Arrays.asList("Ребята, все слушаем меня, главное сохранять спокойствие\n" +
                        "Нужно срочно забаррикадировать входную дверь!\n" +
                        "Мальчики переносят шкафы, девочки несут парты и стулья, ЖИВО!\n", "");
                timerText(schoolPlayView, 1, list2, myPlayer, cnt);
                break;
            case 3:
                requireActivity().runOnUiThread(() -> {
                    firstButton.setVisibility(View.VISIBLE);
                    secondButton.setVisibility(View.VISIBLE);
                    thirdButton.setVisibility(View.VISIBLE);
                    firstButton.setText("•\tСтоять растерянно в стороне ");
                    secondButton.setText("•\tЗабаррикадировать дверь партой ");
                    thirdButton.setText("•\tЗабаррикадировать дверь шкафом ");
                });
                List<String> list3 = Arrays.asList("Ух какой тяжелый/тяжёлая, ели как тащу…",
                        "Ребята нужно срочно поторопиться, в любую минуту может кто-нибудь зайти",
                        "Хух, ну вроде успели");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> {
                    if (myPlayer.sex.equals("male")) {
                        deathDialog(myPlayer, "Мальчики носят шкафы, он куда тяжелее, " +
                                "девочки не смогут его передвинуть, вы не успели забаррикадировать вход.\n" +
                                "К сожалению вы ответили неверно и набрали: ");
                    } else {
                        myPlayer.points += 100;
                        requireActivity().runOnUiThread(() -> schoolPlayView.findViewById(R.id.point1)
                                .setBackgroundResource(R.drawable.style_points_green));
                        timerText(schoolPlayView, 3, list3, myPlayer, cnt);
                    }
                });
                thirdButton.setOnClickListener(view -> {
                    if (myPlayer.sex.equals("female")) {
                        deathDialog(myPlayer, "Вы попытались передвинуть шкаф, но он оказался" +
                                "слишком тяжелым и не сдвинулся с места, вы не успели забаррикадировать вход\n" +
                                "К сожалению вы ответили неверно и набрали: ");
                    } else {
                        myPlayer.points += 100;
                        requireActivity().runOnUiThread(() -> schoolPlayView.findViewById(R.id.point1)
                                .setBackgroundResource(R.drawable.style_points_green));
                        timerText(schoolPlayView, 3, list3, myPlayer, cnt);
                    }
                });
                break;
            case 4:
                List<String> list4 = Arrays.asList("Открываете, полиция!", "");
                timerText(schoolPlayView, 1, list4, myPlayer, cnt);
            case 5:
                List<String> list5 = Arrays.asList("Ищи другого дурочка…\n" +
                        "Подумали вы, но сразу заметили Настю, открывающую дверь терррористу…\n", "");
                requireActivity().runOnUiThread(() -> {
                    firstButton.setText("•\tОткрыть дверь ");
                    secondButton.setText("•\tСидеть молча и ничего не открывать ");
                    thirdButton.setVisibility(View.INVISIBLE);
                });
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(() -> schoolPlayView.findViewById(R.id.point2)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(schoolPlayView, 1, list5, myPlayer, cnt);
                });
        }
    }

    @SuppressLint("SetTextI18n")
    private void deathDialog(Player myPlayer, String textDialog) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.death_dialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        // кнопка закрытия диалогового окна
        TextView text = dialog.findViewById(R.id.textdescription);
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        Button buttonClose = dialog.findViewById(R.id.btncontinue);

        text.setText(textDialog + myPlayer.points + " очков.");
        btnclose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_homePlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });
        buttonClose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_homePlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });
        dialog.show();
    }

    public void changeNavFragment(int navLink) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }

}