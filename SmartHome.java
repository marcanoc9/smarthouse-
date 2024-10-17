package com.example.smarthome;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
// this is a program to run various functions in a smart house, such as turning on&off lights and selecting
//songs to be played on a sound system. the program relies on user input via scanner and two .txt files
// which i have included in the assignment submission.

    public class SmartHome {

        // main method
        public static void main(String[] args) {
            // create instance variables
            double temperature = 24; // temperature in Celsius
            String song = null; // selected song
            String ACSettings; // cooling or heating
            String channel = ""; // selected channel
            boolean isMusicOn = false;
            boolean isTVOn = false;
            boolean roomLights[] = { // all lights off
                    false, // kitchen
                    false, // garage
                    false, // living room
                    false, // bedroom
                    false, // bathroom

            };
            String roomNames[] = {"kitchen", "garage", "living room", "bedroom", "bathroom"};



            Scanner console = new Scanner(System.in);

            // intro message
            System.out.println("Welcome home Sir/Madam! What do you want me to do?");

            while(true) {
                String command = console.nextLine(); // command from user



                 if(command.equals("Play music")) {
                    if(isMusicOn) {
                        System.out.println("Now playing: " + song);
                    }
                    isMusicOn = false;
                    System.out.println("Please select a song");
                    String newsong = console.nextLine();
                    // open songs file to play new song
                    try {
                        Scanner songs = new Scanner(new File("songs.txt"));
                        while(songs.hasNextLine()) {
                            song = songs.nextLine();
                            if(song.contains(newsong)) {
                                System.out.println("Now playing: " + song);
                                isMusicOn = true;
                                break;
                            }
                        }
                        if(!isMusicOn) {
                            System.out.println( newsong + " is not in playlist.");
                        }
                        songs.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if(command.equals("Stop music")) {
                    if(isMusicOn) {
                        isMusicOn = false;
                        System.out.println("No longer playing music.");
                    }
                }
                // changes the temperature and sets AC to cooling or heating
                else if(command.equals("Change temperature")) {
                    System.out.println("What temperature would you like?");
                    double newTemp = console.nextDouble();
                    console.nextLine();
                    if(temperature > newTemp) {
                        ACSettings = "cooling";
                        System.out.println("A/C is now set to: " + ACSettings);
                        temperature = newTemp;
                    }
                    else if(temperature < newTemp) {
                        ACSettings = "heating";
                        System.out.println("A/C is now set to: " + ACSettings);
                        temperature = newTemp;
                    }
                    System.out.print("The temprature is now: ");
                    System.out.println(temperature);
                }
                else if(command.equals("Turn on television")) {
                    if(isTVOn) {
                        System.out.println("Now showing: " + channel);
                    }
                    isTVOn = false;
                    System.out.println("Please select a channel.");
                    String newChannel = console.nextLine();
                    // changes the channel based on user input
                    try {
                        Scanner channels = new Scanner(new File("channels.txt"));
                        while(channels.hasNextLine()) {
                            channel = channels.nextLine();
                            if(channel.equals(newChannel)) {
                                System.out.println("Now showing: " + channel);
                                isTVOn = true;
                                if(roomLights[2]) {
                                    System.out.println("Dim the lights?");
                                    if(console.nextLine().equals("Yes")) {
                                        roomLights[2] = false;
                                        System.out.println(roomNames[2] + "'s lights are turned off.");

                                    }
                                }
                                break;
                            }
                        }
                        if(!isTVOn) {
                            System.out.println( newChannel + " is not available.");
                        }
                        channels.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                else if(command.equals("Turn off television")) {
                    if(isTVOn) {
                        isTVOn = false;
                        System.out.println("Television is now off.");
                    }
                }
                else if(command.equals("Turn on light")) {
                    System.out.println("Which light should I turn on?");
                    String roomname = console.nextLine();
                    boolean isRoomFound = false;
                    // check rooms in room list
                    for(int i=0; i<roomNames.length; i++) {
                        if(roomNames[i].equals(roomname)) {
                            roomLights[i] = true;
                            System.out.println(roomname + "'s lights are turned on.");
                            isRoomFound = true;
                        }
                    }
                    if(!isRoomFound) {
                        System.out.println(roomname + " I can't do that.");
                    }
                }
                else if(command.equals("Turn off light")) {
                    System.out.println("Which light should I turn off?");
                    String roomname = console.nextLine();
                    boolean isRoomFound = false;
                    // check room in room list
                    for(int i=0; i<roomNames.length; i++) {
                        if(roomNames[i].equals(roomname)) {
                            if(roomLights[i]) {
                                roomLights[i] = false;
                                System.out.println(roomname + "'s lights are turned off.");
                            }
                            isRoomFound = true;
                        }
                    }
                    if(!isRoomFound) {
                        System.out.println(roomname + "I can't do that.");
                    }
                }
                else if(command.equals("Make a call")) {
                    // turn off audio producing devices
                    if(isTVOn) {
                        isTVOn = false;
                        System.out.println("Television is now off.");
                    }
                    if(isMusicOn) {
                        isMusicOn = false;
                        System.out.println("Stopped playing music.");
                    }
                    System.out.println("Who would you like to call? Please input a phone number.");
                    String number = console.nextLine();
                    // check for valid number
                    boolean isValidNumber = true;
                    if(number.length() != 10) {
                        isValidNumber = false;
                    }
                    else {
                        for(int i=0;i<10;i++) {
                            if(number.charAt(i) > '9' || number.charAt(i) < '0') {
                                isValidNumber = false;
                            }
                        }
                    }
                    if(isValidNumber) {
                        System.out.println("Calling " + number + " ...");
                    }
                    else {
                        System.out.println(number + " is not a valid phone number");
                    }
                }
                else if(command.equals("Answer doorbell")) {
                    if(isTVOn) {
                        isTVOn = false;
                        System.out.println("Television now off.");
                    }
                    if(isMusicOn) {
                        isMusicOn = false;
                        System.out.println("Stopped playing music.");
                    }
                    System.out.println("What would you like to say?");
                    String message = console.nextLine();
                    System.out.println("Message \"" + message + "\" sent.");
                }
                else if(command.equals("Close system")) {
                    if(isTVOn) {
                        isTVOn = false;
                        System.out.println("Television is now off.");
                    }
                    if(isMusicOn) {
                        isMusicOn = false;
                        System.out.println("Stopped playing music.");
                    }
                    for(int i=0; i<roomLights.length; i++) {
                        if(roomLights[i]) {
                            roomLights[i] = false;
                            System.out.println(roomNames[i] + "'s lights are turned off.");
                        }
                    }
                    System.out.println("Shutting down");
                    console.close();
                    break; // end the loop
                }
                else {
                    System.out.println("I can't do that.");
                }
            }
        }

    }

