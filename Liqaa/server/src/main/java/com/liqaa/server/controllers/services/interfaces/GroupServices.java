package com.liqaa.server.controllers.services.interfaces;

public interface GroupServices
{
    public void createGroup(String groupName, String groupDescription, String groupType, String groupOwner, String groupMembers);
    public void deleteGroup(String groupName);
    public void updateGroup(String groupName, String groupDescription, String groupType, String groupOwner, String groupMembers);
    public void addMembers();
    public void removeMembers();
    public void getGroup(String groupName);
    public void getGroups();
    getGroupMembers();
    getGroupMembers();


    public}
