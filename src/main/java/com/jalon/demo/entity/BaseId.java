package com.jalon.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseId {
    @Id
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name = "sys-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 32)
    private String id;
}
