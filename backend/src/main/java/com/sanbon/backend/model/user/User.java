package com.sanbon.backend.model.user;

import com.sanbon.backend.model.BaseTimeEntity;
import com.sanbon.backend.model.enumclass.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<Bulletin> bulletinList;

    @Builder
    public User(String name, String password, String email, String phone, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public User update(String name, String phone) {
        this.name = name;
        this.phone = phone;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}

