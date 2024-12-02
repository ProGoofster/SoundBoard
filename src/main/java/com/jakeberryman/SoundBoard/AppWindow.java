package com.jakeberryman.SoundBoard;

import com.jakeberryman.SoundBoard.Sounds.ISound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class AppWindow {
    public AppWindow(ArrayList<ISound> soundList){
        JFrame frame = new JFrame("Selecting Toggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        ArrayList<JToggleButton> buttons = new ArrayList<>();

        soundList.forEach(sound -> {

            JToggleButton button = new JToggleButton(sound.getName());

            button.addItemListener(e -> {
                int state = e.getStateChange();

                // if selected print selected in console
                if (state == ItemEvent.SELECTED) {
                    sound.play(button);
                }
                if (state == ItemEvent.DESELECTED){
                    sound.stop();

                }
            });

            frame.add(button);
        });

        frame.setSize(1280, 720 );
        frame.setVisible(true);
    }
}
