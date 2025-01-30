package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Category;
import com.liqaa.shared.models.entities.UserContactCategory;

import java.sql.SQLException;
import java.util.List;

public interface UserContactCategoryRepo {
<<<<<<< HEAD
    public boolean createUserContactCategory(UserContactCategory userContactCategory) throws SQLException;
    public List<UserContactCategory> getAllUserContactCategories() throws SQLException;
    public boolean updateUserContactCategory(UserContactCategory userContactCategory) throws SQLException;
    public boolean deleteUserContactCategory(UserContactCategory userContactCategory) throws SQLException;
=======
    /** int? can be used to return the id of the last inserted row, the number of rows inserted successfully (helpful especially in case of using batch execution) */
    public boolean addNew(UserContactCategory userContactCategory) throws SQLException;
    public List<UserContactCategory> getAll() throws SQLException;
    public boolean update(UserContactCategory userContactCategory) throws SQLException;
    public boolean delete(UserContactCategory userContactCategory) throws SQLException;
>>>>>>> c6df57e (my 30-1 local changes)
    public List<UserContactCategory> getSpecificUserWithCategories(int userId) throws SQLException;
}