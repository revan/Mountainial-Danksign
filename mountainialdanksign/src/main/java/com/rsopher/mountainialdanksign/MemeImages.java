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
                    // TODO: location is kinda off.
                    final ImageView imageView = new ImageView(context);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    params.leftMargin = (int) motionEvent.getX() - location[0];
                    params.topMargin = (int) motionEvent.getY() - location[1];
                    imageView.setLayoutParams(params);

                    imageView.setImageResource(Images.HITMARKER.id);
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
                    return true;
                }
                return false;
            }
        });
        ImageView imageView = new ImageView(context);
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
