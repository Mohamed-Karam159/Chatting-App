package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.UserContactCategory;
import java.util.List;

public interface UserContactCategoryRepo {
    /** int? can be used to return the id of the last inserted row, the number of rows inserted successfully (helpful especially in case of using batch execution) */
    public int addNew(UserContactCategory userContactCategory);
    public UserContactCategory getById(int id);
    public List<UserContactCategory> getAll();
    public boolean update(UserContactCategory userContactCategory);
    public boolean delete(int id);
}