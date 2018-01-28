package ua.com.raido.raidoandroid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.raido.raidoandroid.R;
import ua.com.raido.raidoandroid.activity.LoginActivity;
import ua.com.raido.raidoandroid.interfaces.StateChanger;

public class ResetPasswordFragment extends Fragment{
    private StateChanger changer;

    @BindView(R.id.email)
    EditText emailEditText;

    @BindView(R.id.btn_reset_password)
    Button resetPaasswordButton;

    @BindView(R.id.spinner)
    ProgressBar spinner;

    @BindView(R.id.btn_back)
    Button backButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restore_password, container, false);
        ButterKnife.bind(this, view);
        backButton.setOnClickListener(view1 -> changer.changeState(LoginActivity.State.LOGIN));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changer = (StateChanger) context;
    }
}
