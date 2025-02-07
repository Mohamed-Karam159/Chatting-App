package com.liqaa.client.controllers.services.implementations;

import com.liqaa.client.controllers.services.interfaces.CurrentUser;
import com.liqaa.shared.models.entities.User;

public class CurrentUserImpl implements CurrentUser
{
        private static User currentUser;

        public User getCurrentUser()
        {
            return currentUser;
        }

        public void setCurrentUser(User user)
        {
            currentUser = user;
        }
}



