package ru.antowka.stock.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
        joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)
        },
        inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)
        }
    )
    private Set<Role> authorities;

    @OneToMany(mappedBy = "userId")
    private Set<Position> positions;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Position position) {
        this.positions = positions;
    }
}
