package com.letscode.SWRSocialNetwork.converts;

import com.letscode.SWRSocialNetwork.dto.RebelDTO;
import com.letscode.SWRSocialNetwork.model.Rebel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RebelMapper {

    RebelDTO rebelToRebelDTO(Rebel rebel);

    Rebel rebelDTOToRebel(RebelDTO rebelDTO);
}
