package ObserverInterfaces;

public interface ObservableInterface {

    void addObserver(ObserverInterface o);
    void notifyObservers();
}
