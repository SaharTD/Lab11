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
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotEmpty(message = "the comment should not be empty")
    @Size(min = 5,max = 30)
    @Column(columnDefinition = "varchar(30) not null unique")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private LocalDate comment_date;


    @NotNull(message = "the user id should not be null")
    @Column(columnDefinition = "int not null ")
    private Integer user_id;


    @NotNull(message = "the post id should not be null")
    @Column(columnDefinition = "int not null ")
    private Integer post_id;


}
