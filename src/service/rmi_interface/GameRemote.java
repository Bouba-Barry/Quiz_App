package service.rmi_interface;

import entities.Groups;
import entities.Question;
import entities.Quiz;
import entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface GameRemote extends Remote {

    List<Groups> getAvailableGroups() throws RemoteException;
    long getPlayersScores(Groups group) throws RemoteException;
    boolean endGame(Groups group) throws RemoteException;
    List<Question> getQuestionsForGroup(Groups groups) throws RemoteException;
    PlayerRemote getWinner(Groups group)throws RemoteException;

}
