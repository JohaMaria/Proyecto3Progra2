/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import javafx.animation.Timeline;

/**
 *
 * @author Arturo
 */
public class Winner {
    private String namePlayer;
    private String type;
    private String time;
    
     public Winner() {
        this.namePlayer = "";
        this.type ="";
        this.time ="";
    }
    public Winner(String namePlayer, String type, String time) {
        this.namePlayer = namePlayer;
        this.type = type;
        this.time = time;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
