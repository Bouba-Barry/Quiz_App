package service.rmi_interface;

import entities.Groups;
import entities.Question;
import entities.Quiz;
import entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PlayerRemote extends Remote {

    boolean loginPlayer(String username, String password) throws RemoteException;
    public List<PlayerRemote> getAllPlayersFromGroup(Groups group) throws RemoteException;
    public boolean logoutPlayer(User user) throws RemoteException;
    public boolean registerPlayer(String username, String password) throws RemoteException;
    Groups joinGroup(Groups group) throws RemoteException;
    User getUser() throws RemoteException;
    void submitAnswer(Question question, int answer) throws RemoteException;
    Groups getGroup() throws RemoteException;
    long getPlayerScore() throws RemoteException;
    boolean shouldGameBegin(Groups group) throws RemoteException;

}
