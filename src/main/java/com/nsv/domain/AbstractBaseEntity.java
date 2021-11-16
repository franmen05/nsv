package com.nsv.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract  class AbstractBaseEntity implements Serializable {

    @CreatedBy
    @Column(name = "created_by", nullable = true, length = 50, updatable = false)
//    @JsonIgnore
    private String createdBy;

    @CreatedDate
    private Instant createDate= Instant.now();

    @LastModifiedDate
    private Instant lastUpdateDate = Instant.now();

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
