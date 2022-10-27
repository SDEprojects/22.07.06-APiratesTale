package com.company.models;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {
    static Boolean music_On = true;
    public static void playMusic(String musicLocation) {

        try {
            File musicPath = new File(musicLocation);
            if(musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                //clip.start();



              //  clip.stop();


            }
            else {
                System.out.println("Music file not found!");
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}



