package com.company.models;
import com.apps.util.Prompter;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;


public class Music {
    Prompter prompter = new Prompter(new Scanner(System.in));
    private String musicLocation;
    Clip clip;
    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip.open(audioInput);
                clip.start();
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void stopMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if(musicPath.exists() && clip.isOpen()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip.close();
            }
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




