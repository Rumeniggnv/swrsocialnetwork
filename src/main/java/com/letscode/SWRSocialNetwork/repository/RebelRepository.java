package com.letscode.SWRSocialNetwork.repository;

import com.letscode.SWRSocialNetwork.model.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RebelRepository extends JpaRepository<Rebel, Integer> {
    //Rebel findRebelById(Integer id);
    List<Rebel> findRebelById(Integer id);

    Optional<Rebel> findByName(String rebelName);
}
