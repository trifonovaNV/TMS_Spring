package com.tms.library.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String genre;
}
