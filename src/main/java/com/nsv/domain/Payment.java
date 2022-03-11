package com.nsv.domain;

import com.nsv.exception.NSVException2;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 *
 * @author you_k
 */
@Entity
@Table(name = "payments")
public class Payment   extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    @Column(nullable = false)
    private Double value;

    private String voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;


    @ManyToOne(fetch = FetchType.LAZY)
    private AccountingClosing accountingClosing;

    @Column(nullable = false,name = "invoice_id")
    private Long invoiceId;

    public void add( Double value, String voucher, PaymentType paymentType,
                                 Long invoiceId) {
        this.paymentType = paymentType;
        this.value = value;
        this.invoiceId = invoiceId;

        if(paymentType.getName().equals(PaymentType.CAST))
            this.voucher = voucher;
        else {
            if (StringUtils.hasText(voucher))
                this.voucher = voucher;
            else {
                //                            model.addAttribute("error", "Error: Voucher no encontrado!");
                throw new NSVException2("Voucher Solo puede ser nulo para pagos en efectivo");
            }
        }

    }

    public Payment() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public AccountingClosing getAccountingClosing() {
        return accountingClosing;
    }

    public void setAccountingClosing(AccountingClosing accountingClosing) {
        this.accountingClosing = accountingClosing;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
}
