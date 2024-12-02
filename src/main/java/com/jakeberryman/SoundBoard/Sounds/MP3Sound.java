package com.jakeberryman.SoundBoard.Sounds;

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MP3Sound implements ISound {
    private final File audioPath;
    private Thread playbackThread;
    private AdvancedPlayer player;
    public boolean isPlaying = false;

    public MP3Sound(File audioPath){
        this.audioPath = audioPath;
    }
    @Override
    public void play(JToggleButton button){
        System.out.println("Playing: " + audioPath.getName());
        playbackThread = new Thread(() -> {
            try (FileInputStream fis = new FileInputStream(audioPath)) {
                player = new AdvancedPlayer(fis);
                isPlaying = true;
                player.play();
            } catch (IOException | JavaLayerException e) {
                throw new RuntimeException(e);
            } finally {
                isPlaying = false;
                if(button != null) button.setSelected(false);
            }
        });
        playbackThread.start();
    }
    @Override
    public void play(){
        play(null);
    }

    private void playMp3() throws IOException, JavaLayerException {
        try (FileInputStream fis = new FileInputStream(audioPath)) {
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.play();
            isPlaying = false;
        }
    }
    @Override
    public void stop(){
        if (!isPlaying) return;

        isPlaying = false;

        player.close();
    }
    @Override
    public String getName(){
        return audioPath.getName();
    }

}
