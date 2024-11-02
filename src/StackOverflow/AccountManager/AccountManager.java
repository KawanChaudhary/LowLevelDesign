package StackOverflow.AccountManager;

import StackOverflow.Input.Input;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AccountManager {
    private final Map<String, Member> users;
    private final Input getInput;

    public AccountManager(){
        this.users = new ConcurrentHashMap<>();
        this.getInput = new Input();
    }

    public void addUser(){
        Random rand = new Random();
        int id = rand.nextInt(1000000);

        // we can also add email validation
        String email = getInput.getValidatedInput("Enter Email: ",
                input -> !input.isEmpty() && !users.containsKey(input),
                "Error: Email cannot be empty or already exists.");

        String name = getInput.getValidatedInput("Enter Name: ",
                input -> !input.isEmpty(),
                "Error: Name cannot be empty.");

        String displayName = getInput.getValidatedInput("Enter Display Name: ",
                input -> !input.isEmpty(),
                "Error: Display Name cannot be empty.");

        String password = getInput.getValidatedInput("Enter Password: ",
                input -> !input.isEmpty(),
                "Error: Password cannot be empty.");

        Member newMember = new Member(id, email, name, displayName, password);
        users.put(email, newMember);
        System.out.println("Account created successfully for email: " + email);
    }

    public Member getMemberByEmail(String email){
        Member member = users.get(email);
        if(member != null){
            System.out.println("User found: " + member.getAccount().getDisplayName());
            return member;
        } else {
            System.out.println("Error: No account found with email: " + email);
            return null;
        }
    }

    public void updateUserDisplayName(String email){
        Account account = getMemberByEmail(email).getAccount();
        if(account != null){
            String newDisplayName = getInput.getValidatedInput("Enter new Display Name: ",
                    input -> !input.isEmpty(),
                    "Error: Display Name cannot be empty.");
            account.setDisplayName(newDisplayName);

            System.out.println("User updated successfully: " + account.getDisplayName());
        }
    }

}
