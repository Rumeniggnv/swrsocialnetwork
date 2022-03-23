package com.letscode.SWRSocialNetwork.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private Resource resource;
    private Integer quantity;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "rebel_id")
    private Rebel rebel;
}
