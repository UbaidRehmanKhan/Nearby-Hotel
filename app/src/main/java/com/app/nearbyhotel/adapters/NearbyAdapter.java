package com.app.nearbyhotel.adapters;

import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.app.nearbyhotel.R;
import com.app.nearbyhotel.activities.MainActivity;
import com.app.nearbyhotel.helper.AppConstant;
import com.app.nearbyhotel.helper.Utils;
import com.app.nearbyhotel.model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.Holder> {

    MainActivity mainActivity;
    LayoutInflater inflater;
    ArrayList<Result> resultsList;

    public NearbyAdapter(MainActivity mainActivity) {
        resultsList = new ArrayList<>();
        this.mainActivity = mainActivity;
        inflater = LayoutInflater.from(mainActivity);
    }

    @NonNull
    @Override
    public NearbyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_hotel, viewGroup, false);
        NearbyAdapter.Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyAdapter.Holder holder, int i) {

        if (resultsList.get(i).getPhotos()!=null) {
            String image = AppConstant.HOTLE_PHOT + resultsList.get(i).getPhotos().get(0).getPhotoReference() + AppConstant.HOTLE_PHOTO_KEY;
            Log.e(""+i,""+image );
            Utils.setImageGlide(mainActivity, holder.ivHotel, image.replaceAll("\\s",""));
        }
        else{
            holder.ivHotel.setImageResource(R.drawable.image_placeholder);
        }

        holder.tvRestaurant.setText(resultsList.get(i).getName());


        holder.tvLocation.setText(getCurrentAddress(resultsList.get(i).getGeometry().getLocation().getLat(), resultsList.get(i).getGeometry().getLocation().getLng()));
        if (resultsList.get(i).getRating() != null) {
            String rating = String.valueOf(resultsList.get(i).getRating());
            holder.rating.setRating(Float.parseFloat(rating));
            holder.tvRating.setText(String.valueOf(rating));
            holder.tvReviews.setText(resultsList.get(i).getUserRatingsTotal() + " " + mainActivity.getResources().getString(R.string.reviews));
        }
    }

    public void addAll(ArrayList<Result> resultsList) {
        this.resultsList.clear();
        this.resultsList.addAll(resultsList);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvRestaurant, tvLocation, tvFood, tvRating, tvReviews;
        RatingBar rating;
        ImageView ivHotel;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvRestaurant = itemView.findViewById(R.id.tvRestaurant);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvFood = itemView.findViewById(R.id.tvFood);
            tvRating = itemView.findViewById(R.id.tvRating);
            rating = itemView.findViewById(R.id.rating);
            ivHotel = itemView.findViewById(R.id.ivHotel);
            tvReviews = itemView.findViewById(R.id.tvReviews);
        }
    }

    public String getCurrentAddress(double lat, double lng) {
        try {
            Geocoder geocoder;
            String address = "";
            String country = "";
            List<Address> addresses;
            geocoder = new Geocoder(mainActivity, Locale.getDefault());
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses.size() > 0) {
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            }

            if (addresses.size() > 0) {
                country = addresses.get(0).getCountryName();
            }

            return country + "";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
