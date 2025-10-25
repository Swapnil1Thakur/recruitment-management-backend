package com.recruitment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeFileAddress;
    private String skills;
    private String education;
    private String experience;
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
