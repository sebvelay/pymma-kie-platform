package org.chtijbug.drools.proxy.persistence.repository;

import org.chtijbug.drools.proxy.persistence.model.UserRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends MongoRepository<UserRoles, String> {

    UserRoles findByName(String login);
    UserRoles findByID(String login);
}
