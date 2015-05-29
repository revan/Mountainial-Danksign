package com.rsopher.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.rsopher.mountainialdanksign.MemeSounds;


public class MountainialDanksignDemo extends ActionBarActivity {

    private MemeSounds memeSounds;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountainial_danksign_demo);

        memeSounds = MemeSounds.getInstance();

        ((Button) findViewById(R.id.buttonHorn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memeSounds.play(context, MemeSounds.Sounds.AIRHORN);
            }
        });
    }
}
