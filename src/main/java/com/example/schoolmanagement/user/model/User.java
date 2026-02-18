package com.example.schoolmanagement.user.model;

import com.example.schoolmanagement.role.model.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    @Column(nullable = false, name = "username")
    private String name;
    @Size(max = 50)
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @Column(name = "is_enabled")
    private boolean isEnabled = true;
    @Column(name="is_expired_credential")
    private boolean isExpiredCredential;
    @Column(name="is_expired_account")
    private boolean isExpiredAccount = true;
    @Column(name="is_locked_account")
    private boolean isLockedAccount=true;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public User(String name, String email, @Nullable String encode) {
        this.name = name;
        this.email = email;
        this.password = encode;
    }
}
