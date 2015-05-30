package com.rsopher.mountainialdanksign;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Singleton class responsible for drawing images.
 */
public class MemeImages {
    private static MemeImages instance = null;
    private Handler handler;

    private MemeImages() {
        handler = new Handler();
    }

    public static MemeImages getInstance() {
        if (instance == null) {
            instance = new MemeImages();
        }
        return instance;
    }

    /**
     * Registers an onTouch listener to draw hit indicators on a RelativeLayout
     * @param context
     * @param target
     */
    public void addHitMarkers(final Context context, final RelativeLayout target) {
        target.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // Create ImageView at touch location
                    final ImageView imageView = getImageView(context, Images.HITMARKER);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                    params.leftMargin = (int) motionEvent.getX() - imageView.getWidth()/2;
                    params.topMargin = (int) motionEvent.getY() - imageView.getHeight()/2;
                    target.addView(imageView);

                    // Play sound
                    MemeSounds.getInstance().play(context, MemeSounds.Sounds.HIT);

                    // Schedule hiding for 1 second
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setVisibility(View.GONE);
                        }
                    }, 1 * 1000);
                    return false;
                }
                return false;
            }
        });
    }

    public ImageView getImageView(Context context, Images id) {
        final ImageView imageView = new ImageView(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        imageView.setImageResource(id.id);

        imageView.setLayoutParams(params);

        return  imageView;
    }

    public enum Images {
        AIRHORN(R.drawable.airhorn),
        BRAZZERS(R.drawable.brazzers),
        CANNABIS(R.drawable.cannabis),
        DEALWITHIT(R.drawable.dealwithit),
        DOGE(R.drawable.doge),
        DORITOSBAG(R.drawable.doritosbag),
        DORITOSLOGO(R.drawable.doritoslogo),
        FEDORA(R.drawable.fedora),
        GUN(R.drawable.gun),
        HITMARKER(R.drawable.hitmarker),
        ILLUMINATI(R.drawable.illuminati),
        MOUNTAINDEWCAN(R.drawable.mountaindewcan),
        MOUNTAINDEWLOGO(R.drawable.mountaindewlogo),
        OBEY(R.drawable.obey);

        public int id;
        private Images(int id) {
            this.id = id;
        }
    }
}
