package com.nasor.bookspracticeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title canoot be blank")
    private String title;

    @NotBlank(message = "Author canoot be blank")
    private String author;

    @NotBlank(message = "Description canoot be blank")
    private String description;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Min(value = 1, message = "Publication year must be greater than zero")
    private int publicationDate;
}
