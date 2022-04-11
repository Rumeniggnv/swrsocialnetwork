package com.letscode.SWRSocialNetwork.service;

import com.letscode.SWRSocialNetwork.model.Inventory;
import com.letscode.SWRSocialNetwork.model.Location;
import com.letscode.SWRSocialNetwork.repository.InventoryRepository;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import com.letscode.SWRSocialNetwork.exceptions.RebelNotFoundException;
import com.letscode.SWRSocialNetwork.model.Rebel;
import com.letscode.SWRSocialNetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RebelService {

    private final RebelRepository rebelRepository;
    private final InventoryRepository inventoryRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Rebel> getAll(Pageable pageable) {
        return rebelRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Rebel getById( Integer id) {
        var optionalRebel = rebelRepository.findById( id );
        if ( optionalRebel.isPresent() ) {
            return optionalRebel.get();
        }
        throw new RebelNotFoundException();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Rebel save(Rebel rebel) {
        var rebelSaved = rebelRepository.save(rebel);
        //rebelSaved.getInventory().forEach((inventory) -> inventory.setRebel(rebelSaved));
//        for (Inventory inventory : rebel.getInventory()){
//
//            inventory.setRebel(rebelSaved);
//        }
        //inventoryRepository.saveAll(rebel.getInventory());
        //System.out.println("QTD de inventário: " + rebel.getInventory().size());
        //System.out.println(ArrayUtils.toString(rebel.getInventory().toArray()));
        return rebelSaved;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void delete(Integer id) {
        if ( !rebelRepository.existsById( id ) ) {
            throw new RebelNotFoundException("Rebel does not exist!");
        }
        rebelRepository.deleteById( id );
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public Rebel update(Rebel rebel, Integer rebelId) {
        /*return rebelRepository.findById( rebelId )
                .map(rebelOld -> merge( rebelOld, rebel ) )
                .map(rebelRepository::save)
                .orElseThrow(RebelNotFoundException::new);*/
        Rebel rebelUpdate = getById( rebelId );
        BeanUtils.copyProperties(rebel, rebelUpdate);
        return rebelRepository.save(rebelUpdate);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public Rebel update(Location location, Integer id) {
        Rebel rebel = this.getById( id );
        rebel.setLocation( location );
        return rebelRepository.save( rebel );
    }


}
