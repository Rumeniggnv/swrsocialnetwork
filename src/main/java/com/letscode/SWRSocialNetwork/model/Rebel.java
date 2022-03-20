package com.letscode.SWRSocialNetwork.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Rebel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Genre genre;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Location location;
    @OneToMany(cascade= CascadeType.PERSIST, mappedBy = "rebel", fetch= FetchType.EAGER)
    private List<Inventory> inventories;

    private Integer traitor;
}
