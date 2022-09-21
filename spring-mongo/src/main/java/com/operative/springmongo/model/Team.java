package com.operative.springmongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor

@Document(collection = "teams_copy")
public class Team {
    @Id
    private String id;
    private  String name;
    private String country;
    private String league;
}
