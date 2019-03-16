package com.app.nearbyhotel.webservice;

import android.widget.Toast;

import com.app.nearbyhotel.activities.BaseActivity;
import com.app.nearbyhotel.helper.NetworkUtils;
import com.app.nearbyhotel.model.NearbyHotelWrapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebApiRequest<T> {

    private static webservice apiService;
    private static BaseActivity currentActivity;
    private static WebApiRequest ourInstance = new WebApiRequest();

    private WebApiRequest() {
    }

    public static WebApiRequest getInstance(BaseActivity activity) {
        apiService = WebServiceFactory.getInstance(activity);
        currentActivity = activity;
        return ourInstance;
    }

    public void enqueueCall(Call<NearbyHotelWrapper> call, final APIRequestObjectCallBack apiRequestDataCallBack, final String tag) {
        if (!NetworkUtils.isNetworkAvailable(currentActivity)) {
            Toast.makeText(currentActivity,"No connection" ,Toast.LENGTH_LONG ).show();
            return;
        }


        call.enqueue(new Callback<NearbyHotelWrapper>() {
            @Override
            public void onResponse(Call<NearbyHotelWrapper> call, Response<NearbyHotelWrapper> response) {

                if (response != null && response.body() != null) {
                    if (response.body().getStatus().equals("OK")) {
                        apiRequestDataCallBack.onSuccess(response.body(), tag);
                    } else {
//                        if (response.body().getStatus() == null)
                        Toast.makeText(currentActivity,"Server error" ,Toast.LENGTH_LONG ).show();

                        apiRequestDataCallBack.onError(response.message(), tag);
                    }
                } else {

                    Toast.makeText(currentActivity,"Server error" ,Toast.LENGTH_LONG ).show();
                    apiRequestDataCallBack.onError("Server error", tag);
                }
            }

            @Override
            public void onFailure(Call<NearbyHotelWrapper> call, Throwable t) {


                Toast.makeText(currentActivity,"Server error" ,Toast.LENGTH_LONG ).show();
            }
        });
        }


    public interface APIRequestObjectCallBack {
        void onSuccess(Object response, String tag);

        void onError(String message, String tag);
    }
}