package observers;

import objects.Player;
import observables.TeamList;

import java.util.Collection;
import java.util.LinkedList;

public class Team implements Observer {

    String teamName;
    private Collection<Player> players;
    private TeamList teamList;

    public Team(String teamName, TeamList teamList){
        this.teamName = teamName;
        this.teamList = teamList;
        this.players = new LinkedList<>();

        teamList.register(this);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public String getTeamName(){
        return teamName;
    }

    @Override
    public void update(){
        for(Player player: players){

        }
    }
}
