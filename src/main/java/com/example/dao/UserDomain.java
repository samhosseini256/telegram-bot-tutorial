package com.example.dao;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class UserDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 7436555439452071647L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(unique = true)
    private long userId;
    private String firstName;
    private Boolean isBot;
    private String lastName;
    private String userName;
    private String languageCode;
    private Boolean canJoinGroups;
    private Boolean canReadAllGroupMessages;
    private Boolean supportInlineQueries;
    private Boolean isPremium;
    private Boolean addedToAttachmentMenu;

}