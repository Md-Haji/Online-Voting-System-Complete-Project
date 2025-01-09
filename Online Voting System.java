import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public void addVote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }
}

class VotingSystem {
    private ArrayList<Candidate> candidates;
    private Map<String, Boolean> voters; // To keep track of who has voted

    public VotingSystem() {
        candidates = new ArrayList<>();
        voters = new HashMap<>();
    }

    public void addCandidate(String name) {
        candidates.add(new Candidate(name));
    }

    public void vote(String voterId, String candidateName) {
        if (voters.containsKey(voterId)) {
            System.out.println("You have already voted.");
            return;
        }

        for (Candidate candidate : candidates) {
            if (candidate.getName().equalsIgnoreCase(candidateName)) {
                candidate.addVote();
                voters.put(voterId, true);
                System.out.println("Vote casted for " + candidateName);
                return;
            }
        }

        System.out.println("Candidate not found.");
    }

    public void displayResults() {
        System.out.println("\nElection Results:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + ": " + candidate.getVotes() + " votes");
        }
    }
}

public class OnlineVotingApp {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        Scanner scanner = new Scanner(System.in);

        // Add candidates
        votingSystem.addCandidate("Alice");
        votingSystem.addCandidate("Bob");
        votingSystem.addCandidate("Charlie");

        // Voting Process
        String choice;
        do {
            System.out.println("\nOnline Voting System");
            System.out.println("1. Cast Vote");
            System.out.println("2. Display Results");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter your voter ID: ");
                    String voterId = scanner.nextLine();
                    System.out.print("Enter candidate name to vote: ");
                    String candidateName = scanner.nextLine();
                    votingSystem.vote(voterId, candidateName);
                    break;

                case "2":
                    votingSystem.displayResults();
                    break;

                case "3":
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("3"));

        scanner.close();
    }
}