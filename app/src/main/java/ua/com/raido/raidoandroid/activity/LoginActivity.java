package ua.com.raido.raidoandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.raido.raidoandroid.R;
import ua.com.raido.raidoandroid.fragment.RegistrationFragment;
import ua.com.raido.raidoandroid.fragment.ResetPasswordFragment;
import ua.com.raido.raidoandroid.interfaces.StateChanger;

public class LoginActivity extends BaseActivity implements StateChanger {

    @BindView(R.id.btn_login)
    Button loginButton;

    @BindView(R.id.main_container)
    LinearLayout container;

    @BindView(R.id.email)
    EditText emailView;

    @BindView(R.id.password)
    EditText passwordView;

    @BindView(R.id.spinner)
    ProgressBar spinner;

    @BindView(R.id.btn_reset_password)
    Button resetPasswordButton;

    @BindView(R.id.btn_signup)
    Button registrationButton;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        registrationButton.setOnClickListener((v)-> changeState(State.REGISTRATION));
        resetPasswordButton.setOnClickListener((v)-> changeState(State.FORGET_PASSWORD));
    }

    @Override
    public void changeState(State state) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (state){
            case LOGIN:
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    transaction.remove(fragment);
                }
                break;
            case REGISTRATION:
                transaction.replace(R.id.container,new RegistrationFragment());
                break;
            case FORGET_PASSWORD:
                transaction.replace(R.id.container,new ResetPasswordFragment());
                break;
            case CONFIRMATION:
                break;

        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList.size() > 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            for (Fragment fragment : fragmentList) {
                transaction.remove(fragment);
            }
            transaction.commit();
        } else {
            super.onBackPressed();
        }

    }

    public enum State{
        LOGIN,
        REGISTRATION,
        FORGET_PASSWORD,
        CONFIRMATION
    }
}

