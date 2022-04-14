package com.groupmanagement.entity;

import com.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author mehmet
 */
@Data
@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted <> '1'")
@SQLDelete(sql = "UPDATE groups SET deleted = '1' WHERE id = ?")
public class Group extends BaseEntity {

    private String name;

    private Long userId;
}
