package pl.crm.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    private String password;

    @Column(columnDefinition = "tinyint(1)")
    private boolean enabled = true;

   // @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

   // @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

   // @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Case> cases;

    @OneToMany(mappedBy = "user1", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Case> cases1;

    @OneToMany(mappedBy = "user2", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Case> cases2;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Authority> authorities;


    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User() {
    }


    public User(String username, String password, boolean enabled, String firstName, String lastName, String email, Set<Case> cases, Set<Case> cases1, Set<Case> cases2, Set<Authority> authorities, Date created) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cases = cases;
        this.cases1 = cases1;
        this.cases2 = cases2;
        this.authorities = authorities;
        this.created = created;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Case> getCases() {
        return cases;
    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    public Set<Case> getCases1() {
        return cases1;
    }

    public void setCases1(Set<Case> cases1) {
        this.cases1 = cases1;
    }

    public Set<Case> getCases2() {
        return cases2;
    }

    public void setCases2(Set<Case> cases2) {
        this.cases2 = cases2;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
