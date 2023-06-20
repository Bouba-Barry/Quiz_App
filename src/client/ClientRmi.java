package client;

import entities.Groups;
import entities.Question;
import service.rmi_interface.GameRemote;
import service.rmi_interface.PlayerRemote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class ClientRmi {
    private static GameRemote gameRemote;
    private static PlayerRemote playerRemote;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loggedIn = false;

        try {
            playerRemote = (PlayerRemote) Naming.lookup("rmi://localhost:1099/playerRemote");
            gameRemote = (GameRemote) Naming.lookup("rmi://localhost:1099/gameRemote");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        move: while (true) {
            System.out.println("Que souhaitez-vous faire ? (login/register/quit)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("login")) {
                if (loggedIn) {
                    System.out.println("Vous êtes déjà connecté.");
                } else {
                    System.out.println("Entrez votre nom d'utilisateur :");
                    String username = scanner.nextLine();
                    System.out.println("Entrez votre mot de passe :");
                    String password = scanner.nextLine();

                    try {
                        boolean success = playerRemote.loginPlayer(username, password);
                        if (success) {
                            System.out.println("Connexion réussie !");
                           // System.out.println(success);
                            System.out.println("Mr: "+playerRemote.getUser().getUsername());
                            loggedIn = true;
                            break move;
                        } else {
                            loggedIn = false;
                            System.out.println(success);
                            System.out.println("Échec de la connexion. Veuillez vérifier vos informations d'identification.");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (input.equalsIgnoreCase("register")) {
                if (loggedIn) {
                    System.out.println("Vous êtes déjà connecté.");
                    break move;
                } else {
                    System.out.println("Entrez votre nom d'utilisateur :");
                    String username = scanner.nextLine();
                    System.out.println("Entrez votre mot de passe :");
                    String password = scanner.nextLine();

                    try {
                        boolean success = playerRemote.registerPlayer(username, password);
                        if (success) {
                            System.out.println("Inscription réussie !");
                            loggedIn = true;
                            break move;
                        } else {
                            System.out.println("Échec de l'inscription. Nom d'utilisateur déjà utilisé.");
                            loggedIn = false;
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (input.equalsIgnoreCase("quit")) {
                if (loggedIn) {
                    try {
                        playerRemote.logoutPlayer(playerRemote.getUser());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            } else {
                System.out.println("Commande invalide. Veuillez réessayer.");
            }
        }

        if (loggedIn) {
            beginGame();
        }
    }
    static void beginGame(){
        try {
            List<Groups>  availableGroups = gameRemote.getAvailableGroups();
            System.out.println("Groupes disponibles :");
            for (Groups group : availableGroups) {
                System.out.println(group.getNomGroup());
            }
            System.out.println("Entrez le nom du groupe auquel vous souhaitez rejoindre :");
            String groupName = scanner.nextLine();
            Groups joinedGroup = null;
            for(Groups group: availableGroups){
                if(group.getNomGroup().equalsIgnoreCase(groupName)){
                    joinedGroup = playerRemote.joinGroup(group);
                }
            }
           quit: if(joinedGroup != null) {
                System.out.println("Vous avez rejoint le groupe : " + joinedGroup.getNomGroup());
                int choice;
               quit0: do {
                    System.out.println("-----------------Menu Game ----------------------------------");
                    System.out.println("1 . pour commencer le jeu ");
                    System.out.println("2 . pour vous deconnecter ");
                    choice = scanner.nextInt();
                    boolean moveOn = false;
                    if (choice == 1) {
                        move:while(playerRemote.shouldGameBegin(joinedGroup) == false) {
                                System.out.println("nombre joueurs : " + playerRemote.getAllPlayersFromGroup(joinedGroup).size());
                                System.out.println("pas assez de joueurs disponibles : ");
                                System.out.println("1. quitter ");
                                System.out.println("2. attendre ");
                                int ans = scanner.nextInt();
                                if (ans == 1) {
                                    moveOn = true;
                                    break move;
                                } else if(ans == 2){
                                    continue move;
                                }
                        }
                    if(moveOn == false){
                        List<Question> questions = gameRemote.getQuestionsForGroup(joinedGroup);
                        int tour = questions.size();
                        do {
                            System.out.println("Le jeu a commencé, vous avez " + questions.size() + " questions ...");
                            System.out.println("groupe: " + joinedGroup.getNomGroup());
                            for (Question question : questions) {
                                System.out.println(question.getContent());
                                System.out.println("Question : " + question.getContent());
                                System.out.println("Options :");
                                System.out.println("1: " + question.getOption1());
                                System.out.println("2: " + question.getOption2());
                                System.out.println("3: " + question.getOption3());
                                System.out.println("4: " + question.getOption4());
                                System.out.println("Entrez le numéro de l'option choisie :");
                                System.out.println("\t Votre Score: " + playerRemote.getPlayerScore());
                                int rep = scanner.nextInt();
                                playerRemote.submitAnswer(question, rep);
                                tour--;
                                System.out.println(" ");
                            }
                        }
                        while (tour > 0);
                        System.out.println("player winner "+gameRemote.getWinner(joinedGroup));
                    }else{
                        System.out.println(" deconnnexion.... ");
                        break quit;
                    }
                    }
                    else {
                        System.out.println("entre le bon nombre 1 ou 2 ... ");
                        continue quit0;
                    }
                } while(choice != 1 && choice != 2);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}

