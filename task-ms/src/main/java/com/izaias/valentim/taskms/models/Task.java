package com.izaias.valentim.taskms.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_task")
public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String consignorUsername;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> targetUsernames;
    private String nameTask;
    private String detailTask;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsignorUsername() {
        return consignorUsername;
    }

    public void setConsignorUsername(String consignorUsername) {
        this.consignorUsername = consignorUsername;
    }

    public Set<String> getTargetUsernames() {
        return targetUsernames;
    }

    public void setTargetUsernames(Set<String> targetUsernames) {
        this.targetUsernames = targetUsernames;
    }

    public void setNewTargetUsername(String targetUsername) {
        this.targetUsernames.add(targetUsername);
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDetailTask() {
        return detailTask;
    }

    public void setDetailTask(String detailTask) {
        this.detailTask = detailTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
