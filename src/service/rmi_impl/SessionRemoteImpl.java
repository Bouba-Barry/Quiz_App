package service.rmi_impl;

import entities.User;
import service.rmi_interface.SessionRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SessionRemoteImpl extends UnicastRemoteObject implements SessionRemote {
    protected SessionRemoteImpl() throws RemoteException {
    }

    @Override
    public List<User> getAllLoggedUser() throws RemoteException {
        return null;
    }

    @Override
    public void addUserOnline(User user) throws RemoteException {

    }

    @Override
    public boolean logout(User user) throws RemoteException {
        return false;
    }

    @Override
    public boolean isUserOnline(User user) throws RemoteException {
        return false;
    }
}
