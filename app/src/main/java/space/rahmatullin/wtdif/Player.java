package space.rahmatullin.firstapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public String Name;

    public Player(String sex) {
        this.Name = setName(sex);
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
