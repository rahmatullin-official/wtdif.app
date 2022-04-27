package space.rahmatullin.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideStatusBar();
        createStartFragment();


    }

    public void createStartFragment(){
        HomeScreenFragment homeScreen = new HomeScreenFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainScreenFragment, homeScreen);
        ft.commit();
    }

    @SuppressLint("NonConstantResourceId")
    public void changeFragment(int nowFragmentId, int changeFragmentId){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (changeFragmentId){
            case R.id.planeScreenFragment:
                PlaneScreenFragment planeScreenFragment = new PlaneScreenFragment();
                ft.replace(nowFragmentId, planeScreenFragment);
                ft.commit();
                break;
            case R.id.homeScreenFragment:
                HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
                ft.replace(nowFragmentId, homeScreenFragment);
                ft.commit();
                break;
            case R.id.schoolScreenFragment:
                SchoolScreenFragment schoolScreenFragment = new SchoolScreenFragment();
                ft.replace(nowFragmentId, schoolScreenFragment);
                ft.commit();
                break;
        }

    }
    public void hideStatusBar() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}