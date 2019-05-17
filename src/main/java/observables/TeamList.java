package observables;

import objects.Position;
import objects.Tile;
import observers.Observer;
import observers.Team;
import objects.maps.Map;

import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;

public class TeamList implements Observable{

    private Collection<Observer> observers;
    private HashMap<String, Collection<Tile>> teamTiles;
    private Map map;

    public TeamList(Map map){
        observers = new LinkedList<>();
        teamTiles = new HashMap<>();
        this.map = map;
    }

    public void addTeam(Team team){
        teamTiles.put(team.getTeamName(), new LinkedList<>());
    }

    public Collection<Tile> getTeamTiles(String teamName){
        return teamTiles.get(teamName);
    }

    public void revealTile(String teamName, Position position){
        Tile tile = map.getTile(position);
        Collection<Tile> revealedTiles = getTeamTiles(teamName);
        if(!revealedTiles.contains(tile)) {
            revealedTiles.add(tile);
        }
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
        observers.add(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer observer: observers){
            observer.update();
        }
    }
}
