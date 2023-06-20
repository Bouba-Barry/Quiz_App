package service.rmi_interface;

import entities.Question;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void displayQuestion(Question question) throws RemoteException;
}
