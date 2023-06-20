package service.rmi_interface;

import entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SessionRemote extends Remote {


    public List<User> getAllLoggedUser() throws RemoteException;
    public void addUserOnline(User user) throws RemoteException;
    public boolean logout(User user) throws RemoteException;
    public boolean isUserOnline(User user) throws RemoteException;
}