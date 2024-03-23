package data;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    // List of all the users for the session
    private static List<User> userList;

    // Loads all user data from a file
    public static void loadUsers() {
        System.out.println("Loading data...");
        // Gets all the saved data from the file
        try {
            // Get input file
            FileInputStream inputFile = new FileInputStream("src/database/users.data");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);

            // Retrieve the user data
            userList = (List<User>) inputStream.readObject();

            // Close the input file
            inputStream.close();
            inputFile.close();
        } catch (IOException | ClassNotFoundException error) {
            System.out.println("IO error!");
        }

        // If userList is null then make it empty
        if (userList == null) userList = new ArrayList<>();
    }

    // Saves all user data to a file
    public static void saveUsers() {
        System.out.println("Saving data...");
        // Saves the list of users in a file
        try {
            // Get output file
            FileOutputStream outputFile = new FileOutputStream("src/database/users.data");
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);

            // Write the user data
            outputStream.writeObject(userList);

            // Close the output file
            outputStream.close();
            outputFile.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    // Returns the list of users and their data
    public static List<User> getUserList() {
        return Storage.userList;
    }

    // Adds a user in the system
    public static void addUser(User user) {
        Storage.userList.add(user);
    }

    // Removes a user from the system
    public static void removeUser(User user) {
        Storage.userList.remove(user);
    }
}
