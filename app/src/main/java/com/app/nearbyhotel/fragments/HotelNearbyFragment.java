package com.app.nearbyhotel.fragments;


import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.nearbyhotel.R;
import com.app.nearbyhotel.adapters.NearbyAdapter;
import com.app.nearbyhotel.databinding.FragmentHotelNearbyBinding;
import com.app.nearbyhotel.helper.AppConstant;
import com.app.nearbyhotel.interfaces.LocationCompletion;
import com.app.nearbyhotel.model.NearbyHotelWrapper;
import com.app.nearbyhotel.model.Result;
import com.app.nearbyhotel.webservice.WebApiRequest;

import java.util.ArrayList;


public class HotelNearbyFragment extends BaseFragment implements WebApiRequest.APIRequestObjectCallBack {

    FragmentHotelNearbyBinding binding;

    NearbyAdapter nearbyAdapter;

    ArrayList<Result> resultsList;

    public HotelNearbyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hotel_nearby, container, false);

        setData();

        return binding.getRoot();
    }

    private void setData() {

        mainActivity.getCurrentLocation(new LocationCompletion() {
            @Override
            public void onLocationChanged(Location location) {

                String latLng = location.getLatitude() + "," + location.getLongitude();
                Log.d("Location:", location.toString());
                WebApiRequest.getInstance(mainActivity).enqueueCall(apiService.nearby(latLng, 1500, AppConstant.RESTURANT, "AIzaSyASsrdsiQ9XYq0Pd6lU-o4lxBRrFE_hUno"), HotelNearbyFragment.this, AppConstant.ServerAPICalls.NEARBY_HOTEL);
                mainActivity.showLoader();
            }
        });

        nearbyAdapter = new NearbyAdapter(mainActivity);
        resultsList = new ArrayList<>();
        binding.rvHotel.setLayoutManager(new LinearLayoutManager(mainActivity));
        binding.rvHotel.setAdapter(nearbyAdapter);

    }

    @Override
    public void onSuccess(Object response, String tag) {

        mainActivity.hideLoader();
        resultsList = ((NearbyHotelWrapper) response).getResults();
        nearbyAdapter.addAll(resultsList);
        nearbyAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError(String message, String tag) {

    }

}
