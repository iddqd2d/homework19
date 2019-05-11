package com.homework18.model;


import com.homework18.annotation.InjectRandomInt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @InjectRandomInt(min = 1000, max = 10000)
    private Integer balance;

    @Email
    private String email;
}
