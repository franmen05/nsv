package com.nsv.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "taxes_group")
public class TaxGroup   extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private String description;
    
    @NotEmpty
    private Double value;
    
                    
    @Enumerated(EnumType.STRING)
    private GenericStatus status;
    
        //TODO: Analizar si estas  lista  de  factura se debe mantener en  aqui
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "taxGroup",cascade = CascadeType.ALL)
    private List<Tax> taxes;

    public TaxGroup(Long id) {
        this.id = id;
    }

    public TaxGroup() { }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double price) {
        this.value = price;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public GenericStatus getStatus() {
        return status;
    }
    
    
}
