package assignment.observables;

import assignment.observers.Observer;

public interface Observable {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();
}
