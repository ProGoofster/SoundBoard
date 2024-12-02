package com.jakeberryman.SoundBoard;

import com.jakeberryman.SoundBoard.Sounds.ISound;
import com.jakeberryman.SoundBoard.Sounds.MP3Sound;
import com.jakeberryman.SoundBoard.Sounds.MultiSound;

import java.io.File;
import java.util.ArrayList;

public class RandomSoundPlayer {
    public static void main(String[] args) {
        RandomSoundPlayer randomSoundPlayer = new RandomSoundPlayer("audioFiles");
    }

    private File audioFolder;
    private File[] audioFiles;
    ArrayList<ISound> soundList;

    public RandomSoundPlayer(String pathName){
        soundList = new ArrayList<>();

        createSoundList(pathName);

        new AppWindow(soundList);
    }

    public void createSoundList(String pathName){
        audioFolder = new File(pathName);

        audioFiles = audioFolder.listFiles();

        if (audioFiles != null) {
            for (File audioPath : audioFiles){
                soundList.add(createSound(audioPath));
            }
        }
    }

    public void printFileList(){
        for (File audioPath : audioFiles){
            System.out.println(audioPath.getAbsolutePath());
        }
    }

    public static ISound createSound(File audioPath){
        if (audioPath.isDirectory()) return  new MultiSound(audioPath);
        if (audioPath.getName().endsWith(".mp3")) return new MP3Sound(audioPath);
        //if (audioPath.getName().endsWith(".wav")) return new WavSound(audioPath);
        return null;
    }

}
