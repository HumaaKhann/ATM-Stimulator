package utils;

public class ChangeBalanceById {


        void changeBalanceById(int id, double balance){
            
        //fetch user by Id --> function in userSearch class
        //delete user details from file by creating a function for it
        //create new user with updated balance with function SaveUser in userfileUtil class
       
        usermodel user = new usermodel();   
        UserSearch userSearch = new UserSearch();
        user= userSearch.findUserById(id);
        }
}

       