package com.letscode.SWRSocialNetwork.converts;

import com.letscode.SWRSocialNetwork.dto.RebelDTO;
import com.letscode.SWRSocialNetwork.model.Rebel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;

//@Component
@Mapper(componentModel = "spring")
public interface RebelMapper {

    RebelDTO rebelToRebelDTO(Rebel rebel);

    Rebel rebelDTOTORebel(RebelDTO rebelDTO);
}
