package com.letscode.SWRSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.SWRSocialNetwork.model.Genre;
import com.letscode.SWRSocialNetwork.model.Inventory;
import com.letscode.SWRSocialNetwork.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record RebelDTO(
        Long id,
        @NotBlank
        String name,
        @NotNull
        Integer age,
        @NotNull
        Genre genre,
        @NotNull
        Integer traitor,
        @NotNull
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Location location,
        @NotNull
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<Inventory> inventories
) { }
