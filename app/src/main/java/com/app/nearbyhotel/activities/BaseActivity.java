package com.app.nearbyhotel.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.app.nearbyhotel.R;
import com.app.nearbyhotel.fragments.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public Boolean networkStatus;
    private BaseFragment baseFragment;

    private int mainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void setFrame(int mainFrame) {
        this.mainFrame = mainFrame;

    }



    public void replaceFragment(BaseFragment frag, boolean isAddToBackStack, boolean animate) {
        baseFragment = frag;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(mainFrame, frag, frag.getClass().getSimpleName());

        if (isAddToBackStack) {
            transaction.addToBackStack(null).commitAllowingStateLoss();
        } else {
            transaction.commitAllowingStateLoss();
        }
    }

    public void clearBackStack(int count) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount() - count; i++) {
            fragmentManager.popBackStack();
        }
    }

}
