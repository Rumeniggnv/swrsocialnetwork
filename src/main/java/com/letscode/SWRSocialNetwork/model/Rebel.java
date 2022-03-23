package com.letscode.SWRSocialNetwork.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Rebel implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private Genre genre;

    @OneToMany(mappedBy="rebel")
    private List<Inventory> inventory = new ArrayList<>();

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Location location;


    private Integer traitor;
}
