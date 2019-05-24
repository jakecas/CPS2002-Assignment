package assignment.observables;

import assignment.objects.Position;
import assignment.objects.Tile;
import assignment.observers.Observer;
import assignment.objects.maps.Map;

import java.util.Collection;
import java.util.LinkedList;

public class Team implements Observable{

    private Collection<Observer> observers;
    private Collection<Tile> revealedTiles;
    private Map map;

    public Team(Map map){
        observers = new LinkedList<>();
        revealedTiles = new LinkedList<>();
        this.map = map;
    }

    public boolean isRevealed(Position position){
        return isRevealed(map.getTile(position));
    }

    public boolean isRevealed(Tile tile){
        return revealedTiles.contains(tile);
    }

    public void revealTile(Position position){
        Tile tile = map.getTile(position);
        if(!revealedTiles.contains(tile)) {
            revealedTiles.add(tile);
        }
    }

    public int getTeamSize(){
        return observers.size();
    }

    public void endTurn(){
        notifyObservers();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer observer: observers){
            observer.update();
        }
    }
}
