package com.letscode.SWRSocialNetwork.controller;

import com.letscode.SWRSocialNetwork.converts.LocationMapper;
import com.letscode.SWRSocialNetwork.converts.RebelMapper;
import com.letscode.SWRSocialNetwork.dto.LocationDTO;
import com.letscode.SWRSocialNetwork.dto.RebelDTO;
import com.letscode.SWRSocialNetwork.model.Rebel;
import com.letscode.SWRSocialNetwork.service.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/rebel")
@RequiredArgsConstructor
@Transactional
public class RebelController {

    private final RebelService rebelService;

    private final RebelMapper rebelMapper;
    private final LocationMapper locationMapper;

    @GetMapping("/all-rebels")
    @Cacheable(value = "rebels")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll(@PageableDefault(size = 5)
                                    @SortDefault.SortDefaults({
                                    @SortDefault(sort = "name", direction = Sort.Direction.ASC) })
                                                Pageable pageable){
        //var list = rebelService.getAll().stream().map(rebelMapper::rebelToRebelDTO).toList();
        Page<Rebel> rebels = rebelService.getAll(pageable);
        return ResponseEntity.ok(rebels.toList());
    }

    @GetMapping("/rebel/{id}")
    public RebelDTO getById(@PathVariable Integer id){
        var rebelDTO = rebelMapper.rebelToRebelDTO(rebelService.getById( id ));
        return rebelDTO;
    }

    @PostMapping("register-rebel")
    public ResponseEntity<RebelDTO> save(@Valid @RequestBody RebelDTO rebelDTO){
        //System.out.printf("--> Inventory: %d %n", rebelDTO.inventory().size());
        var rebel = rebelService.save(rebelMapper.rebelDTOToRebel(rebelDTO));

        return ResponseEntity.ok(rebelMapper.rebelToRebelDTO(rebel));
    }

    @PutMapping("/update-rebel/{id}")
    public ResponseEntity<RebelDTO> update(@Valid @RequestBody RebelDTO rebelDTO, @PathVariable Integer id){
        var rebel = rebelService.update(rebelMapper.rebelDTOToRebel(rebelDTO), id);
        return ResponseEntity.ok(rebelMapper.rebelToRebelDTO(rebel));
    }

    @PutMapping("/update-location/{id}")
    public ResponseEntity<?> updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable Integer id){
        var rebel = rebelService.update(locationMapper.locationDTOToLocation(locationDTO), id);
        return ResponseEntity.ok(locationMapper.locationToLocationDTO(rebel.getLocation()));
    }

    @PutMapping("/report-traitor/{id}")
    public ResponseEntity<String> reportTraitor(@PathVariable Integer id) {
        var rebel = rebelService.getById( id );
        rebel.setTraitor( rebel.getTraitor() + 1 );
        rebelService.save( rebel );
        return ResponseEntity.ok("Rebel traitor reported successfully");
    }

//    @PutMapping("/negotiate-inventory/")
//    public ResponseEntity<?> negotiateInventory(@RequestBody List<RebelDTO> rebelsDTO) {
//
//    }

    @DeleteMapping("/delete-rebel/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        rebelService.delete( id );
        return ResponseEntity.ok().build();
    }
}
