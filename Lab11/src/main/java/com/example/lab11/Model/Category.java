package com.example.lab11.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;


    @NotEmpty(message = "the user should not be empty")
    @Size(min = 5,max = 20 , message = "the name should be at lest 5 letters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name ;
}
