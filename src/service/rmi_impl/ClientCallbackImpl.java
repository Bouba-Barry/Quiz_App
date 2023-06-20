package service.rmi_impl;

import entities.Question;
import service.rmi_interface.ClientCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {
    protected ClientCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void displayQuestion(Question question) throws RemoteException {
        System.out.println("Question : " + question.getContent());
        System.out.println("Options :");
        System.out.println("1: " + question.getOption1());
        System.out.println("2: " + question.getOption2());
        System.out.println("3: " + question.getOption3());
        System.out.println("4: " + question.getOption4());
    }
}
