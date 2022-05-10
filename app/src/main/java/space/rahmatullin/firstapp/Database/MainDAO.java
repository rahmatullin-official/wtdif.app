package space.rahmatullin.firstapp.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import space.rahmatullin.firstapp.Models.Users;

@Dao
public interface MainDAO {

    @Insert(onConflict = REPLACE)
    void insert(Users user);

    @Query("SELECT * FROM users ")
    List<Users> getAll();

    @Query("UPDATE users SET name = :name, coins = :coins, skin = :skin, " +
            "points_home = :points_home, points_plane = :points_plane, points_school = :points_school WHERE ID = :id")
    void update(int id, String name, int coins, int skin, int points_home, int points_plane, int points_school);

    @Delete
    void delete(Users user);
}
