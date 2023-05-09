package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundResource implements Resource<Sound> {

    private final Sound sound;

    public SoundResource(Sound sound) {
        this.sound = sound;
    }

    public SoundResource(FileHandle fileHandle) {
        this(Gdx.audio.newSound(fileHandle));
    }

    public SoundResource(String internalPath) {
        this(Gdx.files.internal(internalPath));
    }

    @Override
    public Sound getInternal() {
        return sound;
    }

    @Override
    public Class<Sound> getType() {
        return Sound.class;
    }

    @Override
    public void dispose() {
        sound.dispose();
    }
}
