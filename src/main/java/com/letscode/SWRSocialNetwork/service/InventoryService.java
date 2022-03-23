package com.letscode.SWRSocialNetwork.service;

import com.letscode.SWRSocialNetwork.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
}
