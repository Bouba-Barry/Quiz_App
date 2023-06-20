package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private long score;
    private int groupId;

    public User(String username, String password, long score) {
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, long score, int groupId) {
        this.username = username;
        this.password = password;
        this.score = score;
        this.groupId = groupId;
    }

    public User() {
    }

    public User(int idUser, String username, String password, int groupUser) {
        this.username = username;
        this.id = idUser;
        this.password = password;
        this.groupId = groupUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    static List<User> allsUser(){
        List<User> users = new ArrayList<>();
        users.add(new User("John", "john21"));
        users.add(new User("Alice", "alice21"));
        users.add(new User("Bob", "bob21"));
        users.add(new User("Bouba", "Bouba21"));
        users.add(new User("Rozay", "Rozay21"));
        return users;
    }


}
