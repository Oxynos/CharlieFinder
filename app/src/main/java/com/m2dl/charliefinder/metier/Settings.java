package com.m2dl.charliefinder.metier;

/**
 * Created by veoth on 28/01/16.
 */
public class Settings {

    private int nbClippartToFind;
    private int nbClippartToDisplay;
    private int nbCovering;
    private int givenTime;

    public Settings(int nbClippartToDisplay, int nbClippartToFind, int nbCovering, int givenTime) {
        this.nbClippartToDisplay = nbClippartToDisplay;
        this.nbClippartToFind = nbClippartToFind;
        this.nbCovering = nbCovering;
        this.givenTime = givenTime;
    }

    public void setNbCovering(int nbCovering) {
        this.nbCovering = nbCovering;
    }

    public void setNbClippartToDisplay(int nbClippartToDisplay) {
        this.nbClippartToDisplay = nbClippartToDisplay;
    }

    public void setNbClippartToFind(int nbClippartToFind) {
        this.nbClippartToFind = nbClippartToFind;
    }

    public void setGivenTime(int givenTime) {
        this.givenTime = givenTime;
    }

    public int getNbClippartToFind() {
        return nbClippartToFind;
    }

    public int getNbClippartToDisplay() {
        return nbClippartToDisplay;
    }

    public int getNbCovering() {
        return nbCovering;
    }

    public int getGivenTime() {
        return givenTime;
    }
}
