package StackOverflow.Input;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.function.Predicate;

public class Input {
    private final Scanner scanner;

    public Input(){
        scanner = new Scanner(System.in);
    }
    /**
     * Generic method to get validated input from the user.
     *
     * @param prompt       Message prompt for the user
     * @param validator    Predicate to validate the input
     * @param errorMessage Error message if validation fails
     * @return Validated input as a String
     */
    public @NotNull String getValidatedInput(String prompt, @NotNull Predicate<String> validator, String errorMessage){
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if(validator.test(input)){
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}
