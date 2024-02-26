package Model;

import ObserverInterfaces.ObservableInterface;
import ObserverInterfaces.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class PasswordSession implements ObservableInterface {

    private List<ObserverInterface> observers = new ArrayList<>();
    private int selectedPasswordId;



    @Override
    public void addObserver(ObserverInterface o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : observers) {
            observer.update(this, selectedPasswordId);
        }
    }

    public int getSelectedPasswordId() {
        return selectedPasswordId;
    }

    public void setSelectedPasswordId(int selectedPasswordId) {
        this.selectedPasswordId = selectedPasswordId;
        notifyObservers();
    }
}