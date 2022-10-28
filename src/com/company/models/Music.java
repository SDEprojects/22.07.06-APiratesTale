package com.company.models;
import com.apps.util.Console;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;


public class Music {
    static Boolean music_On = true;
    public  void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if(musicPath.exists()) {
                Scanner scanner = new Scanner(System.in);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                String response ="";

                while(!response.equals("C")) {
                    if(music_On == true) {
                        System.out.println("Music Menu:  Q = Off Music  | C = Continue Music ");
                        System.out.println("Enter your input");
                    }

                    response = scanner.next();
                    response = response.toUpperCase();

                    switch(response) {
                        case ("C"):
                            break;
                        case ("Q"):
                            clip.close();
                            response = "C";
                            break;
                        default:
                            System.out.println("Invalid Input");

                    }
                    System.out.println("End of Menu");
                    Console.pause(1000);
                }

               }
            }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}




