package com.takeshan.springgraphqlpractice.entity;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    Integer companyId;
    String name;

    public Company() {
    }

    public Company(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
