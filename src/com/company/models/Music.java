package com.company.models;
import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.sound.sampled.*;


public class Music {
    static Boolean music_On = true;
    String input ="";
    public  void playMusic(String musicLocation) {

        try {

            File musicPath = new File(musicLocation);
            if(musicPath.exists()) {
                Prompter prompter = new Prompter(new Scanner(System.in));

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                while (!input.equals("C")) {
                    if (music_On == true) {
                        prompter.prompt("Music Menu: ON = music  on | Q = music off | S = Turn Off");
                    } else {
                        prompter.prompt("Music Menu: ON = music  on | Q = music off | S = Turn ON");

                    }
                    input = input.toUpperCase();

                    if(input == "ON"){
                        break;
                    }
                    else if(input == "Q"){
                        clip.close();
                        break;
                    }
                    else if(input == "S"){
                        music_On = false;
                        break;
                    }
                    else{
                        System.out.println("Invalid Input");
                    }

                }
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }




