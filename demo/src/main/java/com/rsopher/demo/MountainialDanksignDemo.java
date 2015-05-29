package com.rsopher.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rsopher.mountainialdanksign.MemeImages;
import com.rsopher.mountainialdanksign.MemeSounds;


public class MountainialDanksignDemo extends ActionBarActivity {

    private MemeSounds memeSounds;
    private MemeImages memeImages;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountainial_danksign_demo);

        memeSounds = MemeSounds.getInstance();

        ImageView horn = ((ImageView) findViewById(R.id.buttonHorn));
        horn.setImageResource(MemeImages.Images.AIRHORN.id);
        horn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memeSounds.play(context, MemeSounds.Sounds.AIRHORN);
            }
        });

        memeImages = MemeImages.getInstance();
        memeImages.addHitMarkers(this, (RelativeLayout) findViewById(R.id.layout));
    }
}
