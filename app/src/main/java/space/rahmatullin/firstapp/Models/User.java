package space.rahmatullin.firstapp.Models;

public class User {
    private String email, pass, name;
    private int coins, skin, points_home, points_plane, points_school;

    public User(){}

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        this.name = "";
        this.coins = 0;
        this.skin = 0;
        this.points_home = 0;
        this.points_plane = 0;
        this.points_school = 0;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getSkin() {
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }

    public int getPoints_home() {
        return points_home;
    }

    public void setPoints_home(int points_home) {
        this.points_home = points_home;
    }

    public int getPoints_plane() {
        return points_plane;
    }

    public void setPoints_plane(int points_plane) {
        this.points_plane = points_plane;
    }

    public int getPoints_school() {
        return points_school;
    }

    public void setPoints_school(int points_school) {
        this.points_school = points_school;
    }
}

