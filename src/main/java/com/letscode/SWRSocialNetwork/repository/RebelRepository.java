package com.letscode.SWRSocialNetwork.repository;

import com.letscode.SWRSocialNetwork.model.Rebel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebelRepository extends CrudRepository<Rebel, Integer> {
    //Rebel findRebelById(Integer id);
    List<Rebel> findRebelById(Integer id);
}
