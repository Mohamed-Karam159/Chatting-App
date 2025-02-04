package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.User;
import com.liqaa.server.util.DatabaseManager;

import java.util.List;
import java.util.Map;


public interface UserInterface {


    boolean isPhoneNumberExists(String phoneNumber);
    boolean isEmailExists(String email) ;

    public int insertNewUser (User user);
    public int deleteUser (int userId); // return 1 if deleted , else 0
    public int updateUser(User user);
    public User getUserById(int userId); //return user has no data if  not found
    public User getUserbyPhone(String userPhone);//return null if  not found
    public User getUserbyPhoneAndPassword(String userPhone, String userPassword); //return null if  not found
    public int getIdByPhoneNumber(String phoneNumber); // return 0 if not found
    public List<User> getAllUsers();

   public Map<String, Integer> getTopCountries();
    public int getNumbersAllUsers();
    public int getNumberAllMaleUsers();
    public int getNumberAllFemaleUsers();
    public int getNumberAllOnlineUsers();
    public int getNumberAllOfflineUsers();
    public int getNumberAllCountryOfUsers(String country);

}
