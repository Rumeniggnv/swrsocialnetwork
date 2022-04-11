package com.letscode.SWRSocialNetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private Resource resource;
    private Integer quantity;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JsonIgnore
    private Rebel rebel;
}
