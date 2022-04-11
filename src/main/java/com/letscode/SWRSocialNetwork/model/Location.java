package com.letscode.SWRSocialNetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String galaxyName;
    private Integer latitude;
    private Integer longitude;

    @OneToMany(mappedBy = "location", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    @JsonIgnore
    private Collection<Rebel> rebels;


}
