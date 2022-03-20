package com.letscode.SWRSocialNetwork.service;

import com.letscode.SWRSocialNetwork.model.Inventory;
import com.letscode.SWRSocialNetwork.repository.InventoryRepository;
import org.apache.commons.lang.StringUtils;
import com.letscode.SWRSocialNetwork.exceptions.RebelNotFoundException;
import com.letscode.SWRSocialNetwork.model.Rebel;
import com.letscode.SWRSocialNetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RebelService {

    private final RebelRepository rebelRepository;
    private final InventoryRepository inventoryRepository;

    public List<Rebel> getAll() {
        return (List<Rebel>) rebelRepository.findAll();
    }

    public Rebel getById( Integer id) {
        var optionalRebel = rebelRepository.findById( id );
        if ( optionalRebel.isPresent() ) {
            return optionalRebel.get();
        }
        throw new RebelNotFoundException();
    }

    public Rebel save(Rebel rebel) {
  //      var rebelSaved = rebelRepository.save(rebel);
    //    rebelSaved.getInventory().forEach((inventory) -> inventory.setRebel(rebelSaved));
//        for (Inventory inventory : rebel.getInventory()){
//
//            inventory.setRebel(new Rebel().toBuilder().id(rebelSaved.getId()).build());
//        }
        //inventoryRepository.saveAll(rebel.getInventory());
        return rebelRepository.save(rebel);//rebelSaved;
    }

    public void delete(Integer id) {
        if ( !rebelRepository.existsById( id ) ) {
            throw new RebelNotFoundException("Rebel does not exist!");
        }
        rebelRepository.deleteById( id );
    }

    public Rebel update(Rebel rebel, Integer rebelId) {
        return rebelRepository.findById( rebelId )
                .map( rebelOld -> merge( rebelOld, rebel ) )
                .map(rebelRepository::save)
                .orElseThrow(RebelNotFoundException::new);
    }

    private Rebel merge( Rebel rebelOld, Rebel rebelNew ) {
        var build = rebelOld.toBuilder();
        if ( StringUtils.isNotEmpty(rebelNew.getName()))
            build.name(rebelNew.getName());
        if (rebelNew.getGenre() != null)
            build.genre((rebelNew.getGenre()));
        if ( rebelNew.getAge() != null )
            build.age(rebelNew.getAge());
        if (rebelNew.getLocation() != null)
            build.location(rebelNew.getLocation());
        if (rebelNew.getInventories() != null)
            build.inventories(rebelNew.getInventories());
        if (rebelNew.getTraitor() != null)
            build.traitor(rebelNew.getTraitor());
        return build.build();
    }
}
