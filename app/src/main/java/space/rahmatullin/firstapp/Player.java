package space.rahmatullin.firstapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public String Name;
    public int coins, skin, points, healthPoints;


    public Player(String sex, int coins, int skin, int points) {
        this.Name = setName(sex);
        this.coins = coins;
        this.skin = skin;
        this.points = points;
        this.healthPoints = 3;
    }

    public String setName(String sex) {
        if (sex.equals("male")) {
            List<String> maleNames = Arrays.asList("Damir Rakhmatullin", "Ivan Alexeev", "Vasya Artemov");
            return maleNames.get(new Random().nextInt(maleNames.size()));
        } else {
            List<String> femaleNames = Arrays.asList("Lana Denisova", "Eva Elife", "Sweetie Fox");
            return femaleNames.get(new Random().nextInt(femaleNames.size()));
        }
    }
}
