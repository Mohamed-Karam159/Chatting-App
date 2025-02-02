package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.UserInterface;
import com.liqaa.shared.models.entities.User;
import com.liqaa.server.util.DatabaseManager;
import com.liqaa.shared.models.enums.CurrentStatus;
import com.liqaa.shared.models.enums.Gender;
import com.liqaa.shared.models.enums.FriendRequestStatus;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
public class UserImplementation implements UserInterface {


    public UserImplementation() {
    }
    @Override
   public boolean isPhoneNumberExists(String phoneNumber)
    {
        String query = "Select * from users where phone_number = ?";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
                  statement.setString(1,phoneNumber);
                  ResultSet result = statement.executeQuery();
                  if (result.next())
                  {  //true: If the new current row is valid
                      return true;
                  }

        }catch (SQLException e) {
            System.out.println("Error in isPhoneNumberExists ");
            e.printStackTrace(); // Handle or log the exception properly
        }
        return false; // Default to false if an error occurs
    }
    @Override
    public boolean isEmailExists(String email)
    {
        String query = "Select * from users where email = ?";
        try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {  return true; }

        }catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }
        return false; // Default to false if an error occurs
    }
   @Override
    public int insertNewUser (User user)
    {
        int result=0 ;
           if (isPhoneNumberExists(user.getPhoneNumber()))
           {
               System.out.println("this user exists");

           }
           else
           {
               String query = "INSERT INTO users ("
                       + "phone_number, name, email, profile_picture, password_hash, "
                       + "gender, country, date_of_birth, bio, current_status, is_active"
                       + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
               try(PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
               {

                   // Set parameters
                   statement.setString(1,user.getPhoneNumber());
                   statement.setString(2, user.getDisplayName());
                   statement.setString(3, user.getEmail());
                   statement.setBlob(4, new ByteArrayInputStream(user.getProfilepicture()));
                   statement.setString(5, user.getPasswordHash());
                   statement.setString(6, user.getGender().toString());
                   statement.setString(7, user.getCountry());
                   statement.setDate(8, (user.getDateofBirth() != null) ? (new java.sql.Date(user.getDateofBirth().getTime())) : null);
                   statement.setString(9, user.getBio());
                   statement.setString(10, user.getCurrentstatus().toString());
                   statement.setBoolean(11, user.isActive());

                   /*
            ByteArrayInputStream profilePhoto = new ByteArrayInputStream(user.getProfilePhoto());
            ps.setBlob(11, profilePhoto);
                   * */
                   // Execute the query
                   result =statement.executeUpdate();
                   if (result > 0) {
                       System.out.println("User inserted successfully!");
                   } else {

                       System.out.println("Failed to insert user.");
                   }
               }catch (SQLException e) {
                   e.printStackTrace(); // Handle or log the exception properly
               }
           }
           return result ;
    }

    @Override
    public int deleteUser (int userId)
    {
        int result= 0;
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setInt(1, userId);
            return statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }
        return result ;
    }


    @Override
    public int updateUser(User user)
    {
        User updatedUser =new User();
        int res=0;
        if (isPhoneNumberExists(user.getPhoneNumber()))
        {
            System.out.println("this user exists");
            //update
            String query = "UPDATE users SET  password_hash = ? ," +
                    " name = ?, gender = ?, date_of_birth = ?, country = ?, bio = ?," +
                    " current_status = ?, is_active = ? ,profile_picture = ? WHERE id = ?";
            try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
            {
                //statement.setString(1, user.getPhoneNumber());
                statement.setString(1, user.getPasswordHash());
                //statement.setString(2, user.getEmail());
                statement.setString(2, user.getDisplayName());
                statement.setString(3, user.getGender().name());
                statement.setDate(4, (user.getDateofBirth() != null) ? (new java.sql.Date(user.getDateofBirth().getTime())) : null);
                statement.setString(5, user.getCountry());
                statement.setString(6, user.getBio());
                statement.setString(7, user.getCurrentstatus().toString());
                statement.setBoolean(8, user.isActive());
                ByteArrayInputStream profilePhoto = new ByteArrayInputStream(user.getProfilepicture());
                statement.setBlob(9, profilePhoto);
                statement.setInt(10, user.getId());
                res= statement.executeUpdate();
            }catch(SQLException e) {
                e.printStackTrace(); // Handle or log the exception properly
            }

        }
        else
        {
            System.out.println("this user does not exist");

        }
        return res;

    }


    @Override
    public User getUserById(int userId)
    {
        String query = "SELECT * FROM users WHERE id = ?";
        User user = new User();
    try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
    {
        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();
        if (result.next())
        {
            user.setId(result.getInt("id"));
            user.setPhoneNumber(result.getString("phone_number"));
            user.setPasswordHash(result.getString("password_hash"));
            user.setEmail(result.getString("email"));
            user.setDisplayName(result.getString("name"));
            user.setGender(Gender.valueOf(result.getString("gender").toUpperCase()));
            user.setDateofBirth(result.getDate("date_of_birth") != null ? new java.sql.Date(result.getDate("date_of_birth").getTime()) : null);
            user.setCountry(result.getString("country"));
            user.setBio(result.getString("bio"));
            user.setIsActive(result.getBoolean("is_active"));
            user.setCurrentstatus(CurrentStatus.valueOf(result.getString("current_status").toUpperCase()));
            user.setCreatedAt(result.getTimestamp("created_at")!= null ? LocalTime.from(result.getTimestamp("created_at").toLocalDateTime()) : null);
            Blob profilePhotoBlob = result.getBlob("profile_picture");
            if (profilePhotoBlob != null) {
                int blobLength = (int) profilePhotoBlob.length();
                byte[] profilePhotoBytes = profilePhotoBlob.getBytes(1, blobLength);
                user.setProfilepicture(profilePhotoBytes);
            }
            else {
                user.setProfilepicture(null);
            }
        }
    }catch(SQLException e) {
        e.printStackTrace(); // Handle or log the exception properly
    }
        return user;
}
  @Override
    public User getUserbyPhone(String userPhone)
    {
        String query = "SELECT * FROM users WHERE phone_number = ?";
        User user = new User();
        if (isPhoneNumberExists(userPhone))
        {
            System.out.println("This user exists");
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setString(1, userPhone);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setPhoneNumber(result.getString("phone_number"));
                user.setPasswordHash(result.getString("password_hash"));
                user.setEmail(result.getString("email"));
                user.setDisplayName(result.getString("name"));
                user.setGender(Gender.valueOf(result.getString("gender").toUpperCase()));
                user.setDateofBirth(result.getDate("date_of_birth") != null ? new java.sql.Date(result.getDate("date_of_birth").getTime()) : null);
                user.setCountry(result.getString("country"));
                user.setBio(result.getString("bio"));
                user.setIsActive(result.getBoolean("is_active"));
                user.setCurrentstatus(CurrentStatus.valueOf(result.getString("current_status").toUpperCase()));
                user.setCreatedAt(result.getTimestamp("created_at") != null ? LocalTime.from(result.getTimestamp("created_at").toLocalDateTime()) : null);
                Blob profilePhotoBlob = result.getBlob("profile_picture");
                if (profilePhotoBlob != null) {
                    int blobLength = (int) profilePhotoBlob.length();
                    byte[] profilePhotoBytes = profilePhotoBlob.getBytes(1, blobLength);
                    user.setProfilepicture(profilePhotoBytes);
                } else {
                    user.setProfilepicture(null);
                }
           return user;
            }
        }catch(SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }}
        return null;
    }
    @Override
    public int getIdByPhoneNumber(String phoneNumber)
    {
        String query = "SELECT * FROM users WHERE phone_number = ?";
       int userId = 0;
        if (isPhoneNumberExists(phoneNumber))
        {
            System.out.println("This user exists");

        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            statement.setString(1, phoneNumber);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                userId = result.getInt("id");
            }
        }catch(SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }
        }
        return userId;
    }
    @Override
    public User getUserbyPhoneAndPassword(String userPhone, String userPassword)
    {
        User user=new User();
        if(isPhoneNumberExists(userPhone)) // Phone number valid
        {
            String query = "SELECT password_hash FROM users WHERE phone_number = ?";
            // check password
            try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
            {
                statement.setString(1, userPhone);
                ResultSet result = statement.executeQuery();
                if (result.next())
                {
                    if(result.getString("password_hash").equals(userPassword)) // Retrieve the password
                    {
                        System.out.println("This user exists");
                         user= getUserbyPhone(userPhone);
                        return user;
                    }
                }
            }catch(SQLException e) {
                e.printStackTrace(); // Handle or log the exception properly
            }
        }
            return null;
    }


    @Override
    public List<User> getAllUsers()
    {
        String query = "SELECT * FROM users";
        try (PreparedStatement statement =  DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet res = statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (res.next())
            {
                {
                    User user = new User();
                    user.setId(res.getInt("id"));
                    user.setPhoneNumber(res.getString("phone_number"));
                    user.setPasswordHash(res.getString("password_hash"));
                    user.setEmail(res.getString("email"));
                    user.setDisplayName(res.getString("name"));
                    user.setGender(Gender.valueOf(res.getString("gender").toUpperCase()));
                    user.setDateofBirth(res.getDate("date_of_birth") != null ? new java.sql.Date(res.getDate("date_of_birth").getTime()) : null);
                    user.setCountry(res.getString("country"));
                    user.setBio(res.getString("bio"));
                    user.setCurrentstatus(CurrentStatus.valueOf(res.getString("current_status").toUpperCase()));
                    user.setIsActive(res.getBoolean("is_active"));
                    user.setCreatedAt(res.getTimestamp("created_at")!= null ? LocalTime.from(res.getTimestamp("created_at").toLocalDateTime()) : null);
                    Blob profilePhotoBlob = res.getBlob("profile_picture");
                    if (profilePhotoBlob != null) {
                        int blobLength = (int) profilePhotoBlob.length();
                        byte[] profilePhotoBytes = profilePhotoBlob.getBytes(1, blobLength);
                        user.setProfilepicture(profilePhotoBytes);
                    } else {
                        user.setProfilepicture(null);
                    }

                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Integer> getTopCountries()
    {
        Map<String, Integer> countries = new HashMap<>();
        String query = "SELECT country, COUNT(*) " +
                "AS user_count FROM users GROUP BY country ORDER BY user_count DESC LIMIT 5 ";
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                String country = res.getString("country");
                int userCount = res.getInt("user_count");
                countries.put(country, userCount);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
    public int getNumbersAllUsers() {
        String query = "SELECT COUNT(id) FROM users";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
@Override
        public int getNumberAllMaleUsers()
    {
        String query = "SELECT COUNT(id) FROM users WHERE gender = 'Male'";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int getNumberAllFemaleUsers()
    {
        String query = "SELECT COUNT(id) FROM users WHERE gender = 'Female'";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int getNumberAllOnlineUsers()
    {
        String query = "SELECT COUNT(id) FROM users WHERE is_active = '1' ";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            //check if the resultset has data
           while (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
   @Override
    public int getNumberAllOfflineUsers()
    {
        String query = "SELECT COUNT(id) FROM users WHERE is_active = '0'";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            //check if the resultset has data
            if (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }
    @Override
    public int getNumberAllCountryOfUsers(String country)
    {
        String query = "SELECT COUNT(id) FROM users WHERE country = ?";
        int count = 0;
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query)) {
            statement.setString(1, country);
            ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
