
package com.starwars.view.base;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.starwars.R;
import com.starwars.application.StarWarsApplication;
import com.starwars.internet.InternetCheck;
import com.starwars.internet.InternetCheckListener;

public class BaseActivity extends AppCompatActivity implements InternetCheckListener {

    private InternetCheck internetCheck;

    protected void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        StarWarsApplication.getInstance().setInternetCheckListner(this);

        if (internetCheck == null) {
            internetCheck = new InternetCheck();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(internetCheck, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (internetCheck != null) {
            unregisterReceiver(internetCheck);
            internetCheck = null;
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            showSnackbar(getString(R.string.internet_connected));
        } else {
            showSnackbar(getString(R.string.internet_not_connected));
        }
    }
}

