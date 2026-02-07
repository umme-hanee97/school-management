package com.example.schoolmanagement.role.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "roles")
@Entity
public class Roles {

    @Id
    @Column(nullable = false, updatable = false, name = "role_name")
    private String name;
    @Column(name = "role_description")
    private String description;
    @Column(nullable = false, updatable = false, name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(nullable = false, name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    void createdAt(){
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt(){
        this.updatedAt = LocalDateTime.now();
    }
}
