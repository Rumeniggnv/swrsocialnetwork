package com.letscode.SWRSocialNetwork.converts;

import com.letscode.SWRSocialNetwork.dto.LocationDTO;
import com.letscode.SWRSocialNetwork.model.Location;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDTO locationToLocationDTO(Location location);

    Location locationDTOToLocation(LocationDTO locationDTO);
}
