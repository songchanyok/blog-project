package com.estsoft.blogproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @Builder
    public User(String email, String password){
        this.email=email;
        this.password=password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("user"));
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //계정만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠겨있느지 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용 여부
    @Override
    public boolean isEnabled() {
        return true;
    }


}
