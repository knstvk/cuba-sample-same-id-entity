package com.company.sample.entity;

import com.haulmont.cuba.core.entity.BaseGenericIdEntity;

import javax.persistence.*;

@Table(name = "SAMPLE_DETAILS")
@Entity(name = "sample$Details")
public class Details extends BaseGenericIdEntity<Long> {
    private static final long serialVersionUID = 321036800029851154L;

    @Id
    @Column(name="ID")
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DESCRIPTION")
    private String description;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "details")
    private Customer customer;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}