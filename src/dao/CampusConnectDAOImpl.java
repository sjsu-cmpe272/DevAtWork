package dao;

import model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Sheeban
 */

@Repository
public class CampusConnectDAOImpl implements CampusConnectDAO{

//    @PersistenceContext
//    private EntityManager entityManager;
//
   @Override
   public User addUsersData(User user) {

        //user = entityManager.merge(user);
        return null;
    }
}


