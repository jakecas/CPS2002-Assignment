package observables;

import observers.Observer;

public interface Observable {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
}
