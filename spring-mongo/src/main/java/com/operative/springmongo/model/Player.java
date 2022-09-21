package com.operative.springmongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor

@Document(collection = "players_copy")
public class Player {
    @Id
    private String id;
    private String name;
    private String nationality;
    private int age;
    @DBRef
    private Team team;
//    private String teamId;
//    @Field(value = "team_id")
}
