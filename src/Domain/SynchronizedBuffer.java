package Domain;

// SynchronizedBuffer synchronizes access to a single shared integer.

import Interface.GameMaze;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SynchronizedBuffer {

    private ObservableList<Winner> winnerT = FXCollections.observableArrayList();

    public synchronized void set(String namePlayer, String type) {
        Winner exp = new Winner();
        System.out.println(namePlayer);
        exp.setNamePlayer(namePlayer);
        exp.setType(type);
        exp.setTime(GameMaze.timer);

        winnerT.add(exp);

    }

    public synchronized ObservableList<Winner> get() {

        return winnerT;
    }

} // end class SynchronizedBuffer