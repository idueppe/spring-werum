package io.crowdcode.speedbay.auction.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Entity
public class Bid extends AbstractEntity {

    private static final Logger log = LoggerFactory.getLogger(Bid.class);

    private String email;
    private BigDecimal amount;

    @PreUpdate @PostLoad @PrePersist
    public void preUpdate() {
        log.info(green("Bid with {} was updated."), email);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Bid withEmail(final String email) {
        this.email = email;
        return this;
    }

    public Bid withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "email='" + email + '\'' +
                ", amount=" + amount +
                "} " + super.toString();
    }
}
