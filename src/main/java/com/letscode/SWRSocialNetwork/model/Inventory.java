package com.letscode.SWRSocialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue
    private Integer id;
    private Resource resource;
    private Integer quantity;
    @ManyToOne//(cascade= CascadeType.PERSIST)
    private Rebel rebel;
}
