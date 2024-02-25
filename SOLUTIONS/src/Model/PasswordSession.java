package Model;

import java.util.Observable;

public class PasswordSession extends Observable {
    private int selectedPasswordId;

    public int getSelectedPasswordId() {
        return selectedPasswordId;
    }

    public void setSelectedPasswordId(int selectedPasswordId) {
        this.selectedPasswordId = selectedPasswordId;
        setChanged();
        notifyObservers();
    }
}
