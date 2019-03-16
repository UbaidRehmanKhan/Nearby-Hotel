package com.app.nearbyhotel.webservice;

import com.app.nearbyhotel.helper.AppConstant;
import com.app.nearbyhotel.model.NearbyHotelWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface webservice {



    //Nearby Resturant
    @GET(AppConstant.ServerAPICalls.NEARBY_HOTEL)
    Call<NearbyHotelWrapper> nearby(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("key") String key
    );
}
