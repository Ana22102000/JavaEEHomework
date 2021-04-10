package com.example.demo;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BookModel {

    @NotEmpty(message="Title can't be empty")
    private String title;
    @Pattern(regexp="^[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{5}-[0-9]{1}", message="Isbn has bad format")
    private String isbn;
    @NotEmpty(message="Surname can't be empty")
    private String authorSurname;
    @NotEmpty(message="Name can't be empty")
    private String authorName;

}
