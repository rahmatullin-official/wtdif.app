package space.rahmatullin.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Database;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Objects;

import space.rahmatullin.firstapp.Models.User;
import space.rahmatullin.firstapp.R;


public class AuthScreenFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference dbUsers;

    FrameLayout authFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View authScreenView = inflater.inflate(R.layout.fragment_auth_screen, container, false);

        Button buttonAuth = (Button) authScreenView.findViewById(R.id.button_auth);
        Button buttonRegister = (Button) authScreenView.findViewById(R.id.button_register);

        authFragment = authScreenView.findViewById(R.id.authFragment);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbUsers = db.getReference("Users");

        buttonRegister.setOnClickListener(view -> showRegisterWindow());
        buttonAuth.setOnClickListener(view -> AuthUser(authScreenView));

        return authScreenView;
    }

    private void AuthUser(View authScreenView) {

        TextInputEditText email = authScreenView.findViewById(R.id.emailField);
        TextInputEditText pass = authScreenView.findViewById(R.id.passField);

            if (TextUtils.isEmpty(email.getText().toString())) {
                Snackbar.make(authFragment, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (pass.getText().toString().length() < 5) {
                Snackbar.make(authFragment, "Введите пароль, более 5 символов", Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }
            // Авторизация пользователя

            auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnSuccessListener(authResult -> {
                            changeNavFragment(R.id.action_authScreenFragment_to_homeScreenFragment);
                    }).addOnFailureListener(e -> {
                        Snackbar.make(authFragment, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT)
                                .show();
            });
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Зарегестрироваться");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View registerWindow = inflater.inflate(R.layout.register_window, null);
        dialog.setView(registerWindow);

        final TextInputEditText email = registerWindow.findViewById(R.id.emailField);
        final TextInputEditText pass = registerWindow.findViewById(R.id.passField);
        final TextInputEditText repeatPass = registerWindow.findViewById(R.id.repeatPassField);

        dialog.setNegativeButton("Отменить", (dialogInterface, i) -> dialogInterface.dismiss());
        dialog.setPositiveButton("Отправить", (dialogInterface, i) -> {
            if (TextUtils.isEmpty(email.getText().toString())) {
                Snackbar.make(authFragment, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (pass.getText().toString().length() < 5) {
                Snackbar.make(authFragment, "Введите пароль, более 5 символов", Snackbar.LENGTH_SHORT)
                        .show();
                return;
            }
            if (TextUtils.isEmpty(repeatPass.getText().toString())) {
                Snackbar.make(authFragment, "Повторите пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (!repeatPass.getText().toString().equals(pass.getText().toString())){
                Snackbar.make(authFragment, "Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                return;
            }
            // Регистрация пользователя
            auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnSuccessListener(authResult -> {
                        User user = new User(email.getText().toString(), pass.getText().toString());
                        dbUsers.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnSuccessListener(unused -> {
                            Snackbar.make(authFragment, "Вы успешно зарегестрировались!", Snackbar.LENGTH_SHORT)
                                    .show();
                        });
                    });
        });

        dialog.show();

    }

    private void changeNavFragment(int navLink) {
        NavHostFragment.findNavController(this).navigate(navLink);
    }

}