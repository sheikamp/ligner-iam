package com.aimlesslyfree.ligner.iam.db;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private String name;
}
