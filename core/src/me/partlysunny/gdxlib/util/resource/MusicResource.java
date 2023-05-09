package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class MusicResource implements Resource<Music> {

    private final Music music;

    public MusicResource(Music music) {
        this.music = music;
    }

    public MusicResource(FileHandle path) {
        this(Gdx.audio.newMusic(path));
    }

    public MusicResource(String path) {
        this(Gdx.files.internal(path));
    }

    @Override
    public Music getInternal() {
        return music;
    }

    @Override
    public Class<Music> getType() {
        return Music.class;
    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
