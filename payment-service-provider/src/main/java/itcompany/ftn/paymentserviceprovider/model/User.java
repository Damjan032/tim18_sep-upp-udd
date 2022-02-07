package itcompany.ftn.paymentserviceprovider.model;

import itcompany.ftn.paymentserviceprovider.model.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String bitcoinWallet;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean requiresPasswordChange;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "web_shop_id", referencedColumnName = "id")
    private WebShop managedWebShop;

    public User() {}

    public User(String id, String email, String password, Role role, boolean requiresPasswordChange, boolean active, WebShop managedWebShop) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.requiresPasswordChange = requiresPasswordChange;
        this.active = active;
        this.managedWebShop = managedWebShop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isRequiresPasswordChange() {
        return requiresPasswordChange;
    }

    public void setRequiresPasswordChange(boolean requiresPasswordChange) {
        this.requiresPasswordChange = requiresPasswordChange;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public WebShop getManagedWebShop() {
        return managedWebShop;
    }

    public void setManagedWebShop(WebShop managedWebShop) {
        this.managedWebShop = managedWebShop;
    }
}
