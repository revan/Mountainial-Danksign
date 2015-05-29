package com.rsopher.mountainialdanksign;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.Hashtable;
import java.util.Map;

/**
 * Singleton class responsible for loading and playing sounds.
 * Lazy-loads sounds, and supports concurrent plays.
 * Usage: MemeSounds.getInstance().play(MemeSounds.Sounds.AIRHORN);
 */
public class MemeSounds {
    private static MemeSounds instance = null;

    private SoundPool soundPool;

    private Map<Integer, Integer> loaded;

    private MemeSounds() {
        soundPool = new SoundPool(10, AudioAttributes.USAGE_GAME, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int id, int status) {
                // If we're loading a sound for the first time, play it.
                soundPool.play(id, 1, 1, 69, 0, 1f);
            }
        });
        loaded = new Hashtable<>(Sounds.values().length);
    }

    public static MemeSounds getInstance() {
        if (instance == null) {
            instance = new MemeSounds();
        }
        return instance;
    }

    /**
     * Plays a sound resource if loaded, else loads it.
     * @param context
     * @param id
     */
    public void play(Context context, Sounds id) {
        if (!loaded.containsKey(id.id)) {
            loaded.put(id.id, soundPool.load(context, id.id, 69));
        } else {
            soundPool.play(loaded.get(id.id), 1, 1, 69, 0, 1f);
        }
    }

    /** Add new sounds to res/raw/, then to this enum,*/
    public enum Sounds {
        AIRHORN(R.raw.airhorn),
        AIRHORNS(R.raw.airhorns),
        ANGELS(R.raw.angels),
        DRAMATIC(R.raw.dramatic),
        EXPLOSION(R.raw.dramatic),
        HEADSHOT(R.raw.dramatic),
        HIT(R.raw.hit),
        NOSIGNAL(R.raw.nosignal),
        SLOWMO(R.raw.slowmo),
        SMOKEWEED(R.raw.smokeweed),
        TRIPLE(R.raw.triple),
        WHATTHEFU(R.raw.whatthefu);

        public int id;
        private Sounds(int id) {
            this.id = id;
        }
    }
}
