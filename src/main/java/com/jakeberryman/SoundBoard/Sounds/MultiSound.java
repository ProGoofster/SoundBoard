package com.jakeberryman.SoundBoard.Sounds;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import com.jakeberryman.SoundBoard.RandomSoundPlayer;

public class MultiSound implements ISound {
    private final File audioPath;
    private final ArrayList<ISound> subSounds;
    private ISound playingSound;
    public boolean isPlaying = false;

    public MultiSound(File audioPath) {
        this.audioPath = audioPath;
        subSounds = new ArrayList<>();

        createSoundList();
    }

    public void createSoundList(){
        File[] audioFiles = audioPath.listFiles();

        if (audioFiles != null) {
            for (File audioPath : audioFiles){
                subSounds.add(RandomSoundPlayer.createSound(audioPath));
            }
        }
    }

    @Override
    public void play(JToggleButton button) {
        Random r = new Random();

        playingSound = subSounds.get(r.nextInt(subSounds.size()));
        playingSound.play(button);
    }

    @Override
    public void stop() {
        playingSound.stop();
    }

    @Override
    public void play() {
        play(null);
    }

    @Override
    public String getName() {
        return audioPath.getName() + "\\";
    }
}
