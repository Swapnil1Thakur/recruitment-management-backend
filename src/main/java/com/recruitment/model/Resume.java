package com.recruitment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    @Column(length = 100000)
    private byte[] data;

    @Lob
    @Column(columnDefinition = "text")
    private String skills;

    @Lob
    @Column(columnDefinition = "text")
    private String education;

    @Lob
    @Column(columnDefinition = "text")
    private String experience;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
