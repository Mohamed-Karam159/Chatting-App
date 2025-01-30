package com.liqaa.server.controllers.reposotories.interfaces;

import com.liqaa.shared.models.entities.Group;

import java.util.List;

public interface GroupRepo
{
    public int createGroup(Group group);
    public Group getGroup(int id);
    public boolean updateGroup(Group group);
    public boolean deleteGroup(Group group);
    public List<Group> getGroups(Integer[] ids);
    public List<Group> getGroups(int id);

}