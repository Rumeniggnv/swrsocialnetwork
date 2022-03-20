package com.letscode.SWRSocialNetwork.service;

import com.letscode.SWRSocialNetwork.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
}
