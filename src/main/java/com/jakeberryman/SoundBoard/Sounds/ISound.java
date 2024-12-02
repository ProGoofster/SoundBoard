package com.jakeberryman.SoundBoard.Sounds;

import javax.swing.*;

public interface ISound {
    public boolean isPlaying = false;
    void play(JToggleButton button);
    void stop();
    void play();
    String getName();

}
