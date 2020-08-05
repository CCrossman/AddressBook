package com.crossman;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredUserRepository extends CrudRepository<StoredUser, String> {
}
