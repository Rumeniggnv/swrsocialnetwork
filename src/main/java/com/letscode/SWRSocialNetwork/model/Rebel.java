package com.letscode.SWRSocialNetwork.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serial;
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
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private Genre genre;
    private Integer traitor;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Location location;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "rebel_id")
    private List<Inventory> inventories = new ArrayList<>();

    //@OneToMany(mappedBy="rebel", cascade=CascadeType.PERSIST)
    //private List<Inventory> inventory = new ArrayList<>();

}
