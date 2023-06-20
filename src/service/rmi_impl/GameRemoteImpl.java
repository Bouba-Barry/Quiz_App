package service.rmi_impl;

import entities.Groups;
import entities.Question;
import entities.Quiz;
import entities.User;
import service.rmi_interface.ClientCallback;
import service.rmi_interface.GameRemote;
import service.rmi_interface.PlayerRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class GameRemoteImpl extends UnicastRemoteObject implements GameRemote {
    private  List<Groups> allgroups;
    private static HashMap<Groups, List<Question>> groupsQuestion;
    public GameRemoteImpl() throws RemoteException {}
    void init() {
        groupsQuestion = new HashMap<>();
        allgroups = new ArrayList<>();
        allgroups.add(new Groups("Avangers"));
        allgroups.add(new Groups("titans"));
        allgroups.add(new Groups("neverLose"));
        allgroups.add(new Groups("Terminator"));

        // Ajouter des questions pour chaque groupe dans groupQuestions
        for (Groups group : allgroups) {
            List<Question> questions = new ArrayList<>();
            // Ajoutez vos questions ici pour chaque groupe
            // Exemple pour le groupe "Avangers"
            questions.add(new Question("Quelle est la capitale de la France ?", "Paris", "londre", "Option 3", "Option 4", "Option 1", 5));
            questions.add(new Question("Quelle est la planète la plus proche du soleil ?", "mercure", "terre", "jupiter", "Option 4", "Option 1",8));
            questions.add(new Question("Quel est le plus grand océan du monde ?", "Océan Atlantique", "Océan pacifique", "Océan Indien", "Mortal", "Option 2", 6));
            questions.add(new Question("Qui est Le président de la russie", "markovic", "poutine", "kovic", "metro", "Option 2", 5));
            // Ajouter les questions associées au groupe dans le map groupQuestions
            groupsQuestion.put(group, questions);
        }

        System.out.println("Le HashMap groupsQuestion a été initialisé avec succès.");
    }



    @Override
    public List<Groups> getAvailableGroups() throws RemoteException {
        init();
        return allgroups;
    }

    private List<PlayerRemote> getPlayerGroup(Groups group) throws RemoteException {
        List<PlayerRemote> playerGroup = new ArrayList<>();
        for (PlayerRemote player : PlayerRemoteImpl.loggedUser()) {
            if (player.getGroup().getNomGroup().equals(group.getNomGroup())) {
                playerGroup.add(player);
            }
        }
        return playerGroup;
    }

    @Override
    public List<Question> getQuestionsForGroup(Groups group) throws RemoteException {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Quelle est la capitale de la France ?", "Paris", "londre", "Option 3", "Option 4", "Option 1"));
        questions.add(new Question("Quelle est la planète la plus proche du soleil ?", "mercure", "terre", "jupiter", "Option 4", "Option 1"));
        questions.add(new Question("Quel est le plus grand océan du monde ?", "Océan Atlantique", "Océan pacifique", "Océan Indien", "Mortal", "Option 2"));
        questions.add(new Question("Qui est Le président de la russie", "markovic", "poutine", "kovic", "metro", "Option 2"));
        return questions;
    }


    @Override
    public long getPlayersScores(Groups group) throws RemoteException {
        long score = 0;
        for(PlayerRemote p : getPlayerGroup(group)){
            score += p.getPlayerScore();
        }
        group.setScore(score);
        return score;
    }

    @Override
    public boolean endGame(Groups group) throws RemoteException {
        List<PlayerRemote> playerRemotes = getPlayerGroup(group);
        List<Question> questions = getQuestionsForGroup(group);
        if(playerRemotes.size() < 1 || questions.isEmpty())
            return true;
        else
            return false;
    }
    @Override
    public PlayerRemote getWinner(Groups group) throws RemoteException {
        List<PlayerRemote> players = getPlayerGroup(group);
        long maxScore = 0;
        PlayerRemote winner = null;
        for (PlayerRemote player : players) {
            long score = player.getPlayerScore();
            if (score > maxScore) {
                maxScore = score;
                winner = player;
            }
        }
        return winner;
    }

}
