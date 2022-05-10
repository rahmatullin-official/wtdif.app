package space.rahmatullin.firstapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class Users implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name="email")
    String email = "";

    @ColumnInfo(name="password")
    String password = "";

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "coins")
    int coins = 0;

    @ColumnInfo(name = "skin")
    int skin = 0;

    @ColumnInfo(name = "points_home")
    int points_home = 0;

    @ColumnInfo(name = "points_plane")
    int points_plane = 0;

    @ColumnInfo(name = "points_school")
    int points_school = 0;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
