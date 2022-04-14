package com.usermanagement.entity;


import com.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author mehmet
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted <> '1'")
@SQLDelete(sql = "UPDATE users SET deleted = '1' WHERE id = ?")
public class User extends BaseEntity {

    private String email;

    private String password;

}
