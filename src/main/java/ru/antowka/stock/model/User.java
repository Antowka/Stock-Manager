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

    @Column(name = "login")
    private String login;

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
    private Set<Role> role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    private Set<Position> positions;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Position position) {
        this.positions = positions;
    }
}
