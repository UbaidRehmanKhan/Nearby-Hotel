package com.app.nearbyhotel.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.app.nearbyhotel.activities.MainActivity;
import com.app.nearbyhotel.webservice.WebServiceFactory;
import com.app.nearbyhotel.webservice.webservice;


public class BaseFragment extends Fragment {

    public static webservice apiService;
    MainActivity mainActivity;
    public Activity activity;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        apiService = WebServiceFactory.getInstance((Activity) context);
        mainActivity = (MainActivity) context;
    }

    protected void replaceFragment(BaseFragment frag, boolean isAddToBackStack, boolean animate) {
        mainActivity.replaceFragment(frag, isAddToBackStack, animate);
    }

    protected MainActivity getMainActivity() {
        return (MainActivity) activity;
    }
}
