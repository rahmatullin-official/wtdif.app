package space.rahmatullin.firstapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public String Name, sex;
    public int coins, skin, points;


    public Player(String sex, int coins, int skin, int points) {
        this.Name = setName(sex);
        this.sex = sex;
        this.coins = coins;
        this.skin = setSkin(sex);
        this.points = points;
    }

    public String setName(String sex) {
        if (sex.equals("male"))  {
            List<String> maleNames = Arrays.asList("Дамир", "Ваня", "Леша");
            return maleNames.get(new Random().nextInt(maleNames.size()));
        } else {
            List<String> femaleNames = Arrays.asList("Лана", "Катя", "Лиза");
            return femaleNames.get(new Random().nextInt(femaleNames.size()));
        }
    }

    public int setSkin(String sex){
        if (sex.equals("male") && skin < 2){
            return 0;
        }
        else{
            if(skin < 2) {
                return 1;
            }
        }
        return skin;
    }
}
