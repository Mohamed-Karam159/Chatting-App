package com.liqaa.server.controllers.reposotories.implementations;

import com.liqaa.server.controllers.reposotories.interfaces.UserContactCategoryRepo;
import com.liqaa.shared.models.entities.UserContactCategory;
import java.util.List;

public class UserContactCategoryRepoImpl implements UserContactCategoryRepo {
    @Override
    public int addNew(UserContactCategory userContactCategory) {
        return 0;
    }

    @Override
    public UserContactCategory getById(int id) {
        return null;
    }

    @Override
    public List<UserContactCategory> getAll() {
        return List.of();
    }

    @Override
    public boolean update(UserContactCategory userContactCategory) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
