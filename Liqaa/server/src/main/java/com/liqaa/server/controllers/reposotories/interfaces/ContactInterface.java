package com.liqaa.server.controllers.reposotories.interfaces;
import com.liqaa.shared.models.entities.Contacts;
import com.liqaa.server.util.DatabaseManager;

import java.util.List;

public interface ContactInterface {


    public boolean isContact(int userId, int contactId) ;
    public int createContact(Contacts contact) ;
    public int deleteById(int userId, int contactId);
    public  boolean blockContact(int userId, int contactId) ;
    public  boolean unblockContact(int userId, int contactId);
    public  List<Contacts> getAllContacts (int id);


}
