package server;

import entities.Groups;
import entities.Question;
import entities.Quiz;
import entities.User;
import service.rmi_impl.GameRemoteImpl;
import service.rmi_impl.PlayerRemoteImpl;
import service.rmi_interface.GameRemote;
import service.rmi_interface.PlayerRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerRmi {

    public static void main(String[] args) {
        try {

            GameRemote gameRemote = new GameRemoteImpl();
            PlayerRemote playerRemote = new PlayerRemoteImpl();

            // registre RMI sur le port 1099
            LocateRegistry.createRegistry(1099);
            // Lier les objets distants au registre
            Naming.rebind("rmi://localhost:1099/playerRemote", playerRemote);
            Naming.rebind("rmi://localhost:1099/gameRemote", gameRemote);

            System.out.println("Serveur RMI démarré.");

        } catch (RemoteException e) {
            System.out.println("Erreur lors du démarrage du serveur RMI : " + e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
           e.printStackTrace();
        }

    }
}
