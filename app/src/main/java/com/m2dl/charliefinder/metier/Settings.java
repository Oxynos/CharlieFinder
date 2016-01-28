package com.m2dl.charliefinder.metier;

/**
 * Created by veoth on 28/01/16.
 */
public class Settings {

    private int nbClippartToFind;
    private int nbClippartToDisplay;
    private int nbCovering;
    private int givenTime;

    public static Settings getInstance() {

        if (instance == null) { // Premier appel
            instance = new Settings();
        }

        return instance;
    }

    private static Settings instance;

    /**
     * Default settings "Normal"
     */
    private Settings() {
    }

    public void setEasy() {
        instance.nbClippartToDisplay = 12;
        instance.nbClippartToFind = 3;
        instance.nbCovering = 5;
        instance.givenTime = 20;
    }

    public void setNormal() {
        instance.nbClippartToDisplay = 20;
        instance.nbClippartToFind = 5;
        instance.nbCovering = 3;
        instance.givenTime = 15;
    }

    public void setHard() {
        instance.nbClippartToDisplay = 40;
        instance.nbClippartToFind = 10;
        instance.nbCovering = 2;
        instance.givenTime = 10;
    }

    public void setPerso(int a, int b, int c, int d) {
        instance.nbClippartToDisplay = a;
        instance.nbClippartToFind = b;
        instance.nbCovering = c;
        instance.givenTime = d;
    }

    public int getNbClippartToFind() {
        return instance.nbClippartToFind;
    }

    public int getNbClippartToDisplay() {
        return instance.nbClippartToDisplay;
    }

    public int getNbCovering() {
        return instance.nbCovering;
    }

    public int getGivenTime() {
        return instance.givenTime;
    }
}
