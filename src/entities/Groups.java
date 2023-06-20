package entities;

import java.io.Serializable;
import java.util.List;

public class Groups implements Serializable {
    private int idGroup;
    private String nomGroup;
    private List<User> playerGroup;
    private long score = 0;

    public Groups(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    public Groups(){}

    public Groups(int idGroup, String nomGroup, List<User> playerGroup) {
        this.idGroup = idGroup;
        this.nomGroup = nomGroup;
        this.playerGroup = playerGroup;
    }

    public Groups(int id, String name) {
        this.idGroup = id;
        this.nomGroup = name;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getNomGroup() {
        return nomGroup;
    }

    public void setNomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    public List<User> getPlayerGroup() {
        return playerGroup;
    }

    public void setPlayerGroup(List<User> playerGroup) {
        this.playerGroup = playerGroup;
    }

    static List<Groups> allsGroup(){
        List<Groups> group;
        return null;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
