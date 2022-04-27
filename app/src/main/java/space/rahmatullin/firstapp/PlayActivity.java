package space.rahmatullin.firstapp;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class PlayActivity extends AppCompatActivity implements ActivityInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        createStartFragment();
        hideStatusBar();

    }

    @Override
    public void changeFragment(int nowFragmentId, int changeFragmentId) {

    }

    @Override
    public void hideStatusBar() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void createStartFragment() {
        IntroductionScreenFragment introductionScreen = new IntroductionScreenFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.playScreenFragment, introductionScreen);
        ft.commit();
    }
}
