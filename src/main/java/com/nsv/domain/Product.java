package com.nsv.domain;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "products")
public class Product extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private String description;
    
    private String  unit;
    
//    @NotEmpty
    private Double price;
                
    @Enumerated(EnumType.STRING)
    private GenericStatus status;

    
    @ManyToOne(fetch = FetchType.LAZY)
    private TaxGroup taxGroup;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCatogory category;

    public Product() {
    }

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

    public Double getPrice() {
        if(price==null) return 0.0;
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TaxGroup getTaxGroup() {
        return taxGroup;
    }

    public void setTaxGroup(TaxGroup taxGroup) {
        this.taxGroup = taxGroup;
    }

    public ProductCatogory getCategory() {
        return category;
    }

    public void setCategory(ProductCatogory category) {
        this.category = category;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public String getUnit() {
        if(StringUtils.isEmpty(unit)) 
            return "-";
        
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
