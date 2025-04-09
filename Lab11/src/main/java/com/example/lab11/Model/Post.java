package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;


    @NotEmpty(message = "the title should not be empty")
    @Size(min = 10,max = 20)
    @Column(columnDefinition = "varchar(20) not null")
    private String title ;


    @NotEmpty(message = "the content should not be empty")
    @Size(min = 30,max = 300)
    @Column(columnDefinition = "varchar(300) not null unique")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private LocalDate publish_date;

    @NotNull(message = "the category id should not be null")
    @Column(columnDefinition = "int not null ")
    private Integer categoryId;


    @NotNull(message = "the user id should not be null")
    @Column(columnDefinition = "int not null ")
    private Integer user_id;


}
