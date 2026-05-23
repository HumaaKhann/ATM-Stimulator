package utils;

import model.usermodel;

public class ChangePinById {

    public void changePinById(int id, String newPin) {

        UserSearch userSearch = new UserSearch();
        usermodel user = userSearch.findUserById(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        // Update the pin in memory
        user.setPin(newPin);

        // Rewrite record in file using existing helpers
        DeleteUserById deleteUserById = new DeleteUserById();
        deleteUserById.deleteUserById(id);

        UserFileUtil.saveUser(user);
    }
}

