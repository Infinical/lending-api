package com.calvin.lendingapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity


@Table(name = "loan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "duration")
    private int duration;

    @Column(name = "loan_balance")
    private double loanBalance;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "is_defaulted",columnDefinition = "bool default false")
    private boolean isDefaulted;

    @Column(name = "is_foreClosed", columnDefinition = "bool default false")
    private boolean isForeClosed;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "customer_id", referencedColumnName = "id" )
    private Customer customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public boolean isDefaulted() {
        return isDefaulted;
    }

    public void setDefaulted(boolean defaulted) {
        isDefaulted = defaulted;
    }

    public void setForeClosed(boolean foreClosed) {
        isForeClosed = foreClosed;
    }

    public boolean isForeClosed() {
        return isForeClosed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setLoan(this);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setLoan(this);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
        this.loanBalance = this.loanAmount;
        addTime(this.duration);
    }

    @PreUpdate()
    public void setLastUpdate() {
     this.updatedAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addTime(int duration) {
        Calendar cl = Calendar. getInstance();
        cl.setTime(this.createdAt);
         cl.add(Calendar.MONTH, duration);
        this.setExpiryDate(cl.getTime());
    }
}
