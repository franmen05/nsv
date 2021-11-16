package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "users")
public class User   extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;

//    private String password;

    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<UserRol> roles;

    //    @Email
    private String email;
    
    private String comment;
    
    private String address;
//
//    @Transient
//    private String rol;
//
//
//    private GenericStatus status;

    //    @ManyToOne(fetch = FetchType.LAZY)
    //    private Company company;
    
    public User() {
        if(roles== null)
            roles= new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRol> roles) {
        this.roles = roles;
    }

//    public  void setRol(String rol){
//        this.rol=rol;
////        roles.add(new UserRol(this.rol));
//    }

    public  void addRol(Rol rol){
        roles.add(new UserRol(rol));
    }
    public  void clearRol(){
        roles.clear();
    }

//    public String getRol() {
//        return rol;
//    }
}