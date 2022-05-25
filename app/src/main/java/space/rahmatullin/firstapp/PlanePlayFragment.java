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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View planePlayView = inflater.inflate(R.layout.fragment_plane_play, container, false);
        PlanePlayFragmentArgs planePlayFragmentArgs = PlanePlayFragmentArgs.fromBundle(getArguments());
        TextView text = (TextView) planePlayView.findViewById(R.id.coins);
        ImageView player = (ImageView) planePlayView.findViewById(R.id.player);

        String Sex = planePlayFragmentArgs.getSex();
        int coins = planePlayFragmentArgs.getCoins();
        int skin = planePlayFragmentArgs.getSkin();
        int points_home = planePlayFragmentArgs.getPointsPlane();
        Player myPlayer = new Player(Sex, coins, skin, points_home);

        if (myPlayer.skin == 0) {
            player.setImageResource(R.drawable.boy);
        } else if (myPlayer.skin == 1) {
            player.setImageResource(R.drawable.girl);
        }

        List<String> dialogs = Arrays.asList("Даже не вериться что в этом году мы с мамой собрались вдвоём на море! Она всегда занята работой, а я так скучаю по ней…\n" +
                        "Надеюсь мы проведём время замечательно\n", "На лице у мамы я заметил твёрдо засевшее чувство страха, волнения\n",
                "У тебя всё хорошо?\n", "Что? ...Ах, да, прости. Я немного переживаю по поводу полёта", "Не переживай мама, всё будет хорошо",
                "Люблю тебя, солнце. \n" +
                        "Кажется, на наш рейс началась посадка. Покатишь чемодан?\n", "Конечно, мам", "\tСколько нам лететь?", "\tОколо двух часов",
                "Я долго не знал/а чем себя занять, полёт длился мучительно долго. " +
                        "Мама запретила шататься по борту самолёта и приказала сидеть на своём месте", "Если не знаешь чем себя занять, то лучше почитай книгу.",
                "Во всей её сущности я чувствовал раздражённость. Я не стал настаивать на своём,\n" +
                        "Но и к книге не прикоснулся. Моё внимание привлек горящий воспламеняющийся шар.\n" +
                        "С начала я был заворожён его красотой, но в моменте опомнился и сообщил об этом маме\n", "Только не это!", "Мама выцепила взглядом стюардессу," +
                        " та кажется сразу поняла о чём хотела сказать мама, поэтому не успев выслушать её, объяснила нам, что такое бывает, нужно просто сохранять спокойствие!");
        System.out.println(dialogs.size());
        text.setText("\uD83D\uDCB0 " + myPlayer.coins);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.plane_dialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // запрет закрытия кнопкой назад

        // кнопка закрытия диалогового окна
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_planePlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });

        // кнопка продолжить
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(view -> {
            dialog.dismiss();
            timerText(planePlayView, 14, dialogs, myPlayer, 0);
        });

        dialog.show(); // показать диалоговое окно


        return planePlayView;
    }

    private void timerText(View planePlayView, int pause, List<String> dialogs,
                           Player myPlayer, int btncnt) {

        TextView characterText = (TextView) planePlayView.findViewById(R.id.charactertext);
        TextView playerText = (TextView) planePlayView.findViewById(R.id.playertext);
        ImageView character = (ImageView) planePlayView.findViewById(R.id.character);
        ImageView player = (ImageView) planePlayView.findViewById(R.id.player);

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
                    buttonsCnt[0]++;
                    setTextToButtons(planePlayView, buttonsCnt[0], myPlayer);

                }
                if (seconds % 10 == 0) {
                    requireActivity().runOnUiThread(() -> {
                        if (buttonsCnt[0] == 0) {
                            if (cnt[0] == 3 || cnt[0] == 5 || cnt[0] == 8 ||
                                    cnt[0] == 10 || cnt[0] == 12) {
                                playerText.setText("");
                                player.setVisibility(View.INVISIBLE);
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            } else {
                                if (cnt[0] == 7) {
                                    ImageView background = planePlayView.findViewById(R.id.plane_background);
                                    background.setImageResource(R.drawable.inplane);
                                }
                                characterText.setText("");
                                character.setVisibility(View.INVISIBLE);
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if (buttonsCnt[0] == 1) {
                            if(cnt[0] == 1){
                                ImageView character = planePlayView.findViewById(R.id.character);
                                character.setImageResource(R.drawable.stuardessa);
                            }
                            if(cnt[0] == 5 || cnt[0] == 10){
                                ImageView character = planePlayView.findViewById(R.id.character);
                                character.setImageResource(R.drawable.mother);
                            }
                            if (cnt[0] == 8){
                                ImageView character = planePlayView.findViewById(R.id.character);
                                character.setImageResource(R.drawable.pilot);
                            }
                            if (cnt[0] == 1 || cnt[0] == 5 || cnt[0] == 8 || cnt[0] == 10) {
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            } else {
                                character.setVisibility(View.INVISIBLE);
                                characterText.setText("");
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if (buttonsCnt[0] == 2) {
                            if (cnt[0] == 4 || cnt[0] == 6) {
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            } else {
                                character.setVisibility(View.INVISIBLE);
                                characterText.setText("");
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if (buttonsCnt[0] == 3){
                            if(cnt[0] == 0){
                                character.setVisibility(View.INVISIBLE);
                                characterText.setText("");
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            }
                            else{
                                ImageView character = planePlayView.findViewById(R.id.character);
                                character.setImageResource(R.drawable.stuardessa);
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            }
                        }
                        if(buttonsCnt[0] == 4){
                            if(cnt[0] == 1){
                                ImageView character = planePlayView.findViewById(R.id.character);
                                character.setImageResource(R.drawable.mother);
                                player.setVisibility(View.INVISIBLE);
                                playerText.setText("");
                                character.setVisibility(View.VISIBLE);
                                characterText.setText(dialogs.get(cnt[0]));
                            }else{
                                character.setVisibility(View.INVISIBLE);
                                characterText.setText("");
                                player.setVisibility(View.VISIBLE);
                                playerText.setText(dialogs.get(cnt[0]));
                            }
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

    private void setTextToButtons(View planePlayView, int cnt, Player myPlayer) {

        Button firstButton = planePlayView.findViewById(R.id.firstChoiceButton);
        Button secondButton = planePlayView.findViewById(R.id.secondChoiceButton);
        Button thirdButton = planePlayView.findViewById(R.id.thirdChoiceButton);

        switch (cnt) {
            case 1:
                requireActivity().runOnUiThread(() -> {
                    firstButton.setVisibility(View.VISIBLE);
                    secondButton.setVisibility(View.VISIBLE);
                    firstButton.setText("•\tВозмутиться! Мы горим, а нас просят сохранять спокойствие! ...");
                    secondButton.setText("•\tНе поднимать шум. Суета и волнение на борту несут в себе только вред");
                });
                List<String> list = Arrays.asList("Вы же предупредите нас, если ситуация станет критической?",
                        "Конечно, это наша прямая обязанность!", "Хорошо, спасибо за объяснения. ",
                        "Через 5 минут, нас обещали покормить.", "Никогда не пробовал еду самолётах!",
                        "Замечательная возможность попробовать, правда она не такая вкусная, как у меня",
                        "Ха-ха-ха и не поспоришь!", "Мне только принесли еду и мы сразу услышали голос капитана...",
                        "Değerli yolcular. Lütfen oksijen maskeleri takıp sakin olmanızı rica ediyoruz.",
                        "На борту мы не смогли обнаружить ни одной стюардессы, только маски которые выпали сразу же как капитан начал" +
                                " что-то говорить на турецком.", "О господи, что он сказал?!");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> {
                    requireActivity().runOnUiThread(() -> planePlayView.findViewById(R.id.point1)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(planePlayView, 11, list, myPlayer, cnt);
                });
                break;
            case 2:
                requireActivity().runOnUiThread(() -> {
                    thirdButton.setVisibility(View.VISIBLE);
                    firstButton.setText("•\tСказал ни о чём не беспокоиться");
                    secondButton.setText("•\tПопросил принести стакан водички");
                    thirdButton.setText("•\tПопросил надеть маски и сохранят спокойствие");
                });
                List<String> list2 = Arrays.asList("Ты надел/а кислородную маску. Только через некоторое время ты поняла, как сильно тебе не хватает воздуха…",
                        "Через пару минут ты почувствовала, как самолёт с большой скоростью начинает пикировать вниз…", "Не думая и секунды, ты начал подготавливаться к приземлению самолёта, первым делом ты…");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                thirdButton.setOnClickListener(view -> {
                    requireActivity().runOnUiThread(() -> planePlayView.findViewById(R.id.point2)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(planePlayView, 3, list2, myPlayer, cnt);
                });
                break;
            case 3:
                requireActivity().runOnUiThread(() -> {
                    firstButton.setText("•\tПоложил выданый мамой плед аккуратно на колени. ");
                    secondButton.setText("•\tОбнял маму. Может быть это последний шанс прикоснуться к ней?");
                    thirdButton.setText("•\tВзял телефон в руки. После посадки будет что выложить на канал ");
                });
                List<String> list3 = Arrays.asList("Самолёт вошёл в зону турбулентности, нас начало сильно трясти.",
                        "Меня встряхнуло, но к счастью я успел положить мягкий плед на колени, благодаря чему удар не был травмирующим.",
                        "Вроде бы всё закончилось…", "Только у меня возникла эта мысль в голове, как новое, удушающее, уведомления капитана резко ударило по слуху",
                        "Suya inmek zorundayız. Herkes acilen can yeleğini giysin.", "Да сколько можно? Мы не понимаем что он говорит!",
                        "Люди начали надевать спасательные жилеты, вероятно мы приземляемся на воду.", "Срочно, нужен жилет!\n" +
                                "А где его взять?\n");
                firstButton.setOnClickListener(view -> {
                    requireActivity().runOnUiThread(() -> planePlayView.findViewById(R.id.point3)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(planePlayView, 8, list3, myPlayer, cnt);
                });
                secondButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                thirdButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                break;
            case 4:
                requireActivity().runOnUiThread(() -> {
                    firstButton.setText("•\tОтобрать у соседки ");
                    secondButton.setText("•\tУ борт проводника");
                    thirdButton.setText("•\tПод сиденьем ");
                });
                List<String> list4 = Arrays.asList("Я думаю он под креслом.\n" +
                        "Ну же открывайся. бинго!\n", "Все двигаемся к аварийному выходу. Осуществляется посадка на спасательную лодку.");
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                thirdButton.setOnClickListener(view -> {
                    requireActivity().runOnUiThread(() -> planePlayView.findViewById(R.id.point4)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(planePlayView, 8, list4, myPlayer, cnt);
                });
               break;
            case 5:
                List<String> list5 = Arrays.asList("Мама, можно считать этот день вторым днём рождения?", "Думаю что, да\n" +
                                "Главное что мы остались целыми и невредимыми. Приедем домой- купим большой торт!",
                        "Ура! Во время нахождения на спасательной лодке, вы ощутили острое чувство холода .Хорошо что вы взяли с собой тёплую одежду и плед. ",
                        "Вы успешно добрались до дома!");
                requireActivity().runOnUiThread(() -> {
                    thirdButton.setVisibility(View.VISIBLE);
                    firstButton.setText("•\tБежать к выходу ");
                    secondButton.setText("•\tСпокойно двигаться к выходу ");
                    thirdButton.setText("•\tВзять теплую одежду и плед, двигаться к аварийному выходу ");
                });
                firstButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                secondButton.setOnClickListener(view -> deathDialog(myPlayer, "К сожалению вы ответили неверно и набрали: "));
                thirdButton.setOnClickListener(view -> {
                    myPlayer.points += 100;
                    requireActivity().runOnUiThread(() -> planePlayView.findViewById(R.id.point5)
                            .setBackgroundResource(R.drawable.style_points_green));
                    timerText(planePlayView, 4, list5, myPlayer, cnt);
                });
                break;
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
                changeNavFragment(R.id.action_planePlayFragment_to_homeScreenFragment);
            } catch (Exception e) {
            }
            dialog.dismiss();
        });
        buttonClose.setOnClickListener(view -> {
            try {
                changeNavFragment(R.id.action_planePlayFragment_to_homeScreenFragment);
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