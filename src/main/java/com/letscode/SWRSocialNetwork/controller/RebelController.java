package com.letscode.SWRSocialNetwork.controller;

import com.letscode.SWRSocialNetwork.converts.RebelMapper;
import com.letscode.SWRSocialNetwork.dto.RebelDTO;
import com.letscode.SWRSocialNetwork.model.Rebel;
import com.letscode.SWRSocialNetwork.service.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rebel")
@RequiredArgsConstructor
@Transactional
public class RebelController {

    private final RebelService rebelService;

    private final RebelMapper rebelMapper;

    @GetMapping
    @Cacheable(value = "rebels")
    public List<RebelDTO> getAll(){
        var list = rebelService.getAll().stream().map(rebelMapper::rebelToRebelDTO).toList();
        return list;
    }

    @GetMapping("/{id}")
    public RebelDTO getById(@PathVariable Integer id){
        var rebelDTO = rebelMapper.rebelToRebelDTO(rebelService.getById( id ));
        return rebelDTO;
    }

    @PostMapping
    public ResponseEntity<RebelDTO> save(@Valid @RequestBody RebelDTO rebelDTO){
        //System.out.printf("--> Inventory: %d %n", rebelDTO.inventory().size());
        var rebel = rebelService.save(rebelMapper.rebelDTOTORebel(rebelDTO));

        return ResponseEntity.ok(rebelMapper.rebelToRebelDTO(rebel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RebelDTO> update(@Valid @RequestBody RebelDTO rebelDTO, @PathVariable Integer id){
        var rebel = rebelService.update(rebelMapper.rebelDTOTORebel(rebelDTO), id);
        return ResponseEntity.ok(rebelMapper.rebelToRebelDTO(rebel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        rebelService.delete( id );
        return ResponseEntity.ok().build();
    }

}
