package service.rmi_impl;

import entities.Groups;
import entities.Question;
import entities.User;
import service.rmi_interface.PlayerRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerRemoteImpl extends UnicastRemoteObject implements PlayerRemote {
    String answer = "";
    private User user;
    private Groups group;
    private long score;
    private static List<PlayerRemote> playerRemotes;
    static List<User> allUsers;
    public PlayerRemoteImpl() throws RemoteException {
        //allUsers = UserDao.getAllUsers();
        playerRemotes = new ArrayList<>();
        answer = "";
    }
    void init(){
        allUsers = new ArrayList<>();
        allUsers.add(new User("Barry", "barry"));
        allUsers.add(new User("Bouba", "Bouba"));
        allUsers.add(new User("Bob", "Bob"));
    }

    @Override
    public boolean loginPlayer(String username, String password) throws RemoteException {
        init();
        for (User u : allUsers) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                user = new User(username, password);
                user.setScore(0);
                playerRemotes.add(this);
                return true; // Correspondance trouvée, retourne true immédiatement
            }
        }
        return false; // Aucune correspondance trouvée
    }


    @Override
    public List<PlayerRemote> getAllPlayersFromGroup(Groups group) throws RemoteException {
        List<PlayerRemote> playerRemotes1 = new ArrayList<>();
        for (PlayerRemote p : playerRemotes){
            if(p.getGroup().getNomGroup().equals(group.getNomGroup())){
                playerRemotes1.add(p);
            }
        }
        return playerRemotes1;
    }

    @Override
    public boolean logoutPlayer(User user) throws RemoteException {
        for(PlayerRemote p : playerRemotes){
            if(Objects.equals(p.getUser().getUsername(), user.getUsername())){
                playerRemotes.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean registerPlayer(String username, String password) throws RemoteException {

        init();
        for(User u : allUsers){
            if(!u.getUsername().equals(username)){
                allUsers.add(new User(username, password,0));
                return true;
            }
        }
        return  false;
    }

    @Override
    public Groups joinGroup(Groups group) throws RemoteException {
        this.group = group;
        return group;
    }


    @Override
    public User getUser() throws RemoteException {
        User toRet = user;
        System.out.println("user."+toRet.getUsername());
        return toRet;
    }

    @Override
    public void submitAnswer(Question question, int NumAnswer) throws RemoteException {
        String rep = "";
        System.out.println("numeroReponse: "+ NumAnswer);
        switch (NumAnswer) {
            case 1:
                rep = question.getOption1();
                System.out.println("case 1+   "+rep);
                break;
            case 2:
                rep = question.getOption2();
                break;
            case 3:
                rep = question.getOption3();
                break;
            case 4:
                rep = question.getOption4();
                break;
            default:
                rep = "null";
                break;
        }
        this.answer = rep;
        System.out.println("reponse player: "+this.user.getUsername()+ "   repo = "+rep);
        if (question.getCorrectOption().equals(rep)) {
            System.out.println("score === "+ score);
            this.score += question.getPoints();
            System.out.println("score after increase = "+score);
            this.user.setScore(score);
        }
    }


    public static List<PlayerRemote> loggedUser() {
        return playerRemotes;
    }

    @Override
    public Groups getGroup() throws RemoteException {
        return group;
    }

    @Override
    public long getPlayerScore() throws RemoteException {
        System.out.println("score en cours "+score);
        return this.score;
    }

    @Override
    public boolean shouldGameBegin(Groups group) throws RemoteException {
        return getAllPlayersFromGroup(group).size() >= 2;
    }

}
