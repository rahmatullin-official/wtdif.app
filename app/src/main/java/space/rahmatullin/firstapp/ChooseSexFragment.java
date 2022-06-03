package space.rahmatullin.firstapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChooseSexFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View chooseSexView = inflater.inflate(R.layout.fragment_choose_sex, container, false);
        FrameLayout chooseSexFragment;
        ArrayList<Integer> data;

        chooseSexFragment = chooseSexView.findViewById(R.id.chooseSexFragment);

        Button buttonMale = (Button) chooseSexView.findViewById(R.id.button_male);
        Button buttonFemale = (Button) chooseSexView.findViewById(R.id.button_female);

        ChooseSexFragmentArgs chooseSexFragmentArgs = ChooseSexFragmentArgs.fromBundle(getArguments());
        Integer id = chooseSexFragmentArgs.getId();

        data = getDataFromDb(id, chooseSexFragment);
        buttonFemale.setOnClickListener(view -> sendDataFromDb(id, "female", data));
        buttonMale.setOnClickListener(view -> sendDataFromDb(id, "male", data));
//        text.setText(id + "");


        return chooseSexView;
    }

    private ArrayList<Integer> getDataFromDb(Integer location, FrameLayout chooseSexFragment) {
        DatabaseReference myRef;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        assert user != null;
        myRef = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());

        ArrayList<Integer> result = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                GenericTypeIndicator<int> intValues = new GenericTypeIndicator<int>() {
//                };

                Integer coins = snapshot.child("coins").getValue(int.class);
                Integer skin = snapshot.child("skin").getValue(int.class);
                Integer points = 0;

                result.add(coins);
                result.add(skin);

                switch (location) {
                    case 1:
                        points = snapshot.child("points_home")
                                .getValue(int.class);
                        result.add(points);
                        break;
                    case 2:
                        points = snapshot.child("points_plane")
                                .getValue(int.class);
                        result.add(points);
                        break;
                    case 3:
                        points = snapshot.child("points_school")
                                .getValue(int.class);
                        result.add(points);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Snackbar.make(chooseSexFragment, "Ошибка базы данных: " + error, Snackbar.LENGTH_SHORT).show();
            }
        });
        return result;
    }

    private void sendDataFromDb(Integer id, String Sex, ArrayList<Integer> data) throws NullPointerException {
        try {
            int coins = (int) data.get(0);
            int skin = (int) data.get(1);
            int points = (int) data.get(2);
            switch (id) {
                case 1:
                    ChooseSexFragmentDirections.ActionChooseSexFragmentToHomePlayFragment action = ChooseSexFragmentDirections
                            .actionChooseSexFragmentToHomePlayFragment(Sex, coins, skin, points);
                    NavHostFragment.findNavController(this).navigate(action);
                    break;
                case 2:
                    ChooseSexFragmentDirections.ActionChooseSexFragmentToPlanePlayFragment action2 = ChooseSexFragmentDirections
                            .actionChooseSexFragmentToPlanePlayFragment(Sex, coins, skin, points);
                    NavHostFragment.findNavController(this).navigate(action2);
                    break;

                case 3:
                    ChooseSexFragmentDirections.ActionChooseSexFragmentToSchoolPlayFragment action3 = ChooseSexFragmentDirections
                            .actionChooseSexFragmentToSchoolPlayFragment(Sex, coins, skin, points);
                    NavHostFragment.findNavController(this).navigate(action3);
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Ooops bd :(");
        }
    }

    public static void updateData(int coins) {
        DatabaseReference myRef;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        assert user != null;
        myRef = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());

        myRef.child("coins").setValue(coins);
    }

    public static void checkUserMoney(int skin, View myView) {
        DatabaseReference myRef;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        assert user != null;
        myRef = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());
        try {

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer coins = snapshot.child("coins").getValue(int.class);
                    if (coins >= 100) {
                        myRef.child("coins").setValue(coins - 100);
                        myRef.child("skin").setValue(skin);
                        Snackbar.make(myView, "Успешно!", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        } catch (NullPointerException e) {
            System.out.println("oops bd :£");
        }
    }
}