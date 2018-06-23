package pl.crm.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authorities")
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String userName;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private	User user;

    private Date created = new Date();


    public Authority() {
    }

    public Authority(String userName, String authority, User user, Date created) {
        this.userName = userName;
        this.authority = authority;
        this.user = user;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                ", created=" + created +
                '}';
    }
}
