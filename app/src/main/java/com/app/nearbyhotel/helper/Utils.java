package com.app.nearbyhotel.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.nearbyhotel.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class Utils {

    public static void showToast(Context ctx, String text) {
        Toast.makeText(ctx, text + "", Toast.LENGTH_SHORT).show();
    }

    public static void setImageGlide(final Context ctx, final ImageView imageView, String url) {

        Glide.with(ctx)
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true))
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        Log.e("onError", ctx.getResources().getString(R.string.glide_image_error));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView);



    }


}
