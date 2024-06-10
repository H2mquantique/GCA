package com.vibestechsolution.cabinetavocat.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vibestechsolution.cabinetavocat.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {


    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore // prevent serialization
    private List<User> users;
    @CreatedDate // automatically audited and keep tracking about modifications
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedBy // automatically audited and keep tracking about modifications
    @Column(insertable = false) // when we create a new record, we don't need to initialize the value of this attribute
    private LocalDateTime lastModifiedDate;
}
