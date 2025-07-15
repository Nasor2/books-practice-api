package com.nasor.bookspracticeapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Book details")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Book unique identifier (Auto generated)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "Book title", example = "Cien años de soledad")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Schema(description = "Book author", example = "Gabriel Garcia Marquez")
    private String author;

    @NotBlank(message = "Description cannot be blank")
    @Schema(description = "Book description", example = "Una novela fundamental del realismo mágico latinoamericano.")
    private String description;

    @NotBlank(message = "Category cannot be blank")
    @Schema(description = "Book category", example = "Ficción, Realismo Mágico")
    private String category;

    @Min(value = 1, message = "Publication year must be greater than zero")
    @Schema(description = "Book publication year", example = "1967")
    private int publicationDate;
}
