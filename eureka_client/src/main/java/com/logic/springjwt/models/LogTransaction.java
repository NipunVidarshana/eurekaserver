/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.springjwt.models;

/**
 *
 * @author DELL
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "log_transaction")
public class LogTransaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 50)
    private String id;

    @NotBlank
    @Column(name = "table_name")
        @Pattern(regexp = "^$|^[a-zA-Z0-9.,\\s\n\t-@()_/#$: ]+$",message = "Please input valid characters.")
    private String tableName;

    @NotBlank
    @Column(name = "user_name")
        @Pattern(regexp = "^$|^[a-zA-Z0-9.,\\s\n\t-@()_/#$: ]+$",message = "Please input valid characters.")
    private String userName;

    @NotBlank
    @Column(name = "ip_address")
        @Pattern(regexp = "^$|^[a-zA-Z0-9.,\\s\n\t-@()_/#$: ]+$",message = "Please input valid characters.")
    private String ipAddress;

    @NotBlank
    @Column(name = "task")
        @Pattern(regexp = "^$|^[a-zA-Z0-9.,\\s\n\t-@()_/#$: ]+$",message = "Please input valid characters.")
    private String task;

    @NotBlank
    @Column(name = "date_time")
    private String dateTime;

    @NotBlank
    @Column(name = "affected_id")
    @Pattern(regexp = "^$|^[a-zA-Z0-9.,\\s\n\t-@()_/#$: ]+$",message = "Please input valid characters.")
    private String affectedId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAffectedId() {
        return affectedId;
    }

    public void setAffectedId(String affectedId) {
        this.affectedId = affectedId;
    }
}
