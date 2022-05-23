package space.rahmatullin.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import space.rahmatullin.firstapp.R;

public class HomePlayFragment extends Fragment {

    private Dialog dialog;
    private Timer timer;
    private int seconds;
    private int minutes = 50;

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
        ImageView player = (ImageView) homePlayView.findViewById(R.id.player);

        String Sex = homePlayFragmentArgs.getSex();
        int coins = homePlayFragmentArgs.getCoins();
        int skin = homePlayFragmentArgs.getSkin();
        int points_home = homePlayFragmentArgs.getPointsHome();

        Player myPlayer = new Player(Sex, coins, skin, points_home);
        if (myPlayer.skin == 0) {
            player.setImageResource(R.drawable.boy);
        } else if (myPlayer.skin == 1) {
            player.setImageResource(R.drawable.girl);
        }
//        else{
//
//        }
        List<String> dialogs = Arrays.asList("Наконец-то я дома! Сейчас быстренько сделаю все уроки и посмотрю наконец-то мультики!",
                myPlayer.Name + ", подойди ко мне пожалуйста!", "Сейчас подойду, мам …. Что ты хотела?", "Я сейчас срочно должна уехать на работу. " +
                        "На плите стоит суп, через 10 минут он будет готов. Не забудь пожалуйста выключить плиту, хорошо?",
                "Хорошо, мама. Я не забуду.", "Вот и славно, не скучай без меня, как вернусь домой, дам тебе вкусную конфету:3",
                "Ну что, маму проводил, пора садиться за уроки. Автор спустя три часа...", "Что?! Видимо я уснул, сколько времени прошло? " +
                        "И что за странный запах…СУП!", "О НЕТ…НЕТ,НЕТ,НЕТ! Я должен немедленно позвонить ");

        text.setText("\uD83D\uDCB0 " + myPlayer.coins);
        timerText(homePlayView, 9, dialogs, myPlayer, 0);

        return homePlayView;
    }

    private void timerText(View homePlayView, int pause, List<String> dialogs,
                           Player myPlayer, int btncnt) {

        TextView characterText = (TextView) homePlayView.findViewById(R.id.charactertext);
        TextView playerText = (TextView) homePlayView.findViewById(R.id.playertext);
        ImageView character = (ImageView) homePlayView.findViewById(R.id.character);
        ImageView player = (ImageView) homePlayView.findViewById(R.id.player);

        final int[] cnt = {0};
        final int[] buttonsCnt = {btncnt};

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
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
                        ImageView background = homePlayView.findViewById(R.id.house_background);
                        ImageView character = homePlayView.findViewById(R.id.character);

                        character.setImageResource(R.drawable.telephonist);
                        background.setImageResource(R.drawable.housefirebackground);
                    }
                    if (buttonsCnt[0] == 5) {
                        ImageView background = homePlayView.findViewById(R.id.house_background);

                        background.setImageResource(R.drawable.padik);
                    }
                    if (buttonsCnt[0] == 8) {
                        ImageView background = homePlayView.findViewById(R.id.house_background);
                        ImageView character = homePlayView.findViewById(R.id.character);

                        character.setImageResource(R.drawable.fireman);
                        background.setImageResource(R.drawable.street);
                    }
                    buttonsCnt[0]++;
                    setTextToButtons(homePlayView, buttonsCnt[0], myPlayer);
                }
                if (seconds % 10 == 0) {
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (buttonsCnt[0] == 0) {
                                if (cnt[0] == 0 || cnt[0] == 2 || cnt[0] == 4 || cnt[0] > 5) {
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
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            }
                            if (buttonsCnt[0] == 2) {
                                if (cnt[0] == 0) {
                                    characterText.setText(dialogs.get(cnt[0]));
                                } else {
                                    character.setVisibility(View.INVISIBLE);
                                    characterText.setText("");
                                    player.setVisibility(View.VISIBLE);
                                    playerText.setText(dialogs.get(cnt[0]));
                                }
                            }
                            if (buttonsCnt[0] > 2){
                                playerText.setText(dialogs.get(cnt[0]));
                            }
                            cnt[0]++;
                        }
                    });
                }
                seconds--;
//                System.out.println(seconds);
            }
        }, 1000, 1000);
    }

    private void setTextToButtons(View homePlayView, int cnt, Player myPlayer) {
        Button firstButton = homePlayView.findViewById(R.id.firstChoiceButton);
        Button secondButton = homePlayView.findViewById(R.id.secondChoiceButton);
        Button thirdButton = homePlayView.findViewById(R.id.thirdChoiceButton);
        switch (cnt) {
            case 1:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        firstButton.setText("•\tМаме");
                        secondButton.setText("•\tПожарным ");
                        thirdButton.setText("•\tРомке из первого подъезда");

                        firstButton.setVisibility(View.VISIBLE);
                        secondButton.setVisibility(View.VISIBLE);
                        thirdButton.setVisibility(View.VISIBLE);
                    }
                });

                List<String> list = Arrays.asList("Служба спасения. Радиотелефонист Рябченкова. Слушаю вас.", "");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer));
                secondButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point1)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });

                    timerText(homePlayView, 1, list, myPlayer, cnt);
                });
                thirdButton.setOnClickListener(view -> deathDialog(myPlayer));
                break;
            case 2:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        firstButton.setText("•\tРасплакаться в трубку ");
                        secondButton.setText("•\tКричать и просить о помощи ");
                        thirdButton.setText("•\tСообщить о пожаре, назвать адрес и ФИО ");
                    }
                });
                firstButton.setOnClickListener(view -> deathDialog(myPlayer));
                secondButton.setOnClickListener(view -> deathDialog(myPlayer));
                List<String> list2 = Arrays.asList("Помощь уже в пути, ожидайте", "Может стоит попробовать потушить пожар самому?");
                thirdButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point2)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                    timerText(homePlayView, 2, list2, myPlayer, cnt);
                });
                break;
            case 3:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdButton.setVisibility(View.INVISIBLE);
                        firstButton.setText("•\tПопытаться потушить пожар ");
                        secondButton.setText("•\tНичего не делать ");
                    }
                });
                List<String> list3 = Arrays.asList("Я видел в коридоре банку с водой.\n" +
                        "О нет, рядом с очагом возгорания находятся электроприборы.", "");
                firstButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point3)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });

                });
                secondButton.setOnClickListener(view -> deathDialog(myPlayer));
                timerText(homePlayView, 1, list3, myPlayer, cnt);
                break;
            case 4:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdButton.setVisibility(View.INVISIBLE);
                        firstButton.setText("•\tВсё равно тушить пожар");
                        secondButton.setText("•\tОставить эту работу пожарным ");
                    }
                });
                List<String> list4 = Arrays.asList("Ну уж нет, это может быть опасно! Что же делать дальше?", "");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer));
                secondButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point4)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });

                });
                timerText(homePlayView, 1, list4, myPlayer, cnt);
                break;
            case 5:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdButton.setVisibility(View.VISIBLE);
                        firstButton.setText("•\tПредупредить о пожаре соседей ");
                        secondButton.setText("•\tСпасать компьютер из огня ");
                        thirdButton.setText("•\tВ комнате душно, надо проветрить ");
                    }
                });
                List<String> list5 = Arrays.asList("Конечно же стоит предупредить соседей!\n" +
                        "Хм, трубку не берут. Сейчас выйду в коридор и постучусь в дверь.\n", "О нет, здесь клубы дыма! Что же делать?");
                firstButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point5)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                });
                secondButton.setOnClickListener(view -> deathDialog(myPlayer));
                thirdButton.setOnClickListener(view -> deathDialog(myPlayer));
                timerText(homePlayView, 2, list5, myPlayer, cnt);
                break;
            case 6:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        firstButton.setText("•\tЗакрыть дыхательные пути мокрой тряпкой");
                        secondButton.setText("•\tСпрыгнуть в окно ");
                        thirdButton.setText("•\tСвернуться в комочек и расплакаться ");
                    }
                });
                List<String> list6 = Arrays.asList("Хорошо что я не забыл взять мокрое полотенце, так и знал что пригодиться! "
                        , "Глаза режет из-за едкого дыма, что же сделать?");
                firstButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point6)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                });
                secondButton.setOnClickListener(view -> deathDialog(myPlayer));
                thirdButton.setOnClickListener(view -> deathDialog(myPlayer));
                timerText(homePlayView, 2, list6, myPlayer, cnt);
                break;
            case 7:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        firstButton.setText("•\tПередвигаться к выходу на четвереньках ");
                        secondButton.setText("•\tНичего страшного. Потерплю ");
                        thirdButton.setText("•\tБежать наугад ");
                    }
                });
                List<String> list7 = Arrays.asList("Отлично , я смог добраться до лестничной площадки!\n" +
                                "Я так устал, может стоит воспользоваться лифтом?\n", "");
                firstButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point7)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                });
                secondButton.setOnClickListener(view -> deathDialog(myPlayer));
                thirdButton.setOnClickListener(view -> deathDialog(myPlayer));
                timerText(homePlayView, 2, list7, myPlayer, cnt);
                break;
            case 8:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdButton.setVisibility(View.INVISIBLE);
                        firstButton.setText("•\tЭто хорошая идея! ");
                        secondButton.setText("•\tНет. Это может быть опасно! ");
                    }
                });
                List<String> list8 = Arrays.asList("Я не могу так рисковать. Дойду до выхода пешком.", "Хм, кажется лифт сломался. Всё таки хорошо что я на нем не поехал/поехала, " +
                        "не хватало во время пожара застрять в лифте…");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer));
                secondButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point8)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                });

                timerText(homePlayView, 1, list8, myPlayer, cnt);
                break;
            case 9:
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdButton.setVisibility(View.INVISIBLE);
                        secondButton.setVisibility(View.INVISIBLE);
                        firstButton.setText("•\tСообщить им информацию о нахождении очага ");
                    }
                });
                List<String> list9 = Arrays.asList("А вот и пожарные подоспели!",
                        "Здравствуйте, это я вам звонил/звонила. " +
                                "Очаг возгорания в квартире 235 на 6 этаже, поторопитесь!",
                        "Ты огромный/огромная молодец, ты сделал/а все правильно! " +
                                "Спасибо за оперативность и сохранения спокойствия!");
                firstButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            homePlayView.findViewById(R.id.point9)
                                    .setBackgroundResource(R.drawable.style_points_green);
                        }
                    });
                });

                timerText(homePlayView, 3, list9, myPlayer, cnt);
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    private void deathDialog(Player myPlayer) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.death_dialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        // кнопка закрытия диалогового окна
        TextView text = dialog.findViewById(R.id.textdescription);
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        Button buttonClose = dialog.findViewById(R.id.btncontinue);

        text.setText("К сожалению вы ответили неверно и набрали: " + myPlayer.points + " очков.");
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