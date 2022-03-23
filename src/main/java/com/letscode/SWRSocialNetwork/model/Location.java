package com.letscode.SWRSocialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Integer id;
    private String galaxyName;
    private Integer latitude;
    private Integer longitude;
    @OneToMany(mappedBy = "location", cascade= CascadeType.PERSIST, fetch= FetchType.EAGER)
    private Collection<Rebel> rebels;


}
