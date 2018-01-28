package ua.com.raido.raidoandroid.interfaces;

import ua.com.raido.raidoandroid.activity.LoginActivity;


public interface StateChanger{
    void changeState(LoginActivity.State state);
}
