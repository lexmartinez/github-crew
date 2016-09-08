package co.dev.outsider.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.dev.outsider.domain.Profile;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, Long>{}
