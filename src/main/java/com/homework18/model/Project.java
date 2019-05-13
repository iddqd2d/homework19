package com.homework18.model;



import com.homework18.annotation.InjectRandomInt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @InjectRandomInt(min=1000, max =10000)
    @Column
    private Integer balance;

    @Email
    private String email;
}
