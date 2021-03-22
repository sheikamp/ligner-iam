package com.aimlesslyfree.ligner.iam.db;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "friendship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private FriendshipStatus status;
    @ManyToOne
    @ToString.Exclude
    private User user;
    @OneToOne
    @ToString.Exclude
    private User friend;

    enum FriendshipStatus {
        REQUESTED,
        ACCEPTED,
        DENIED
    }
}
