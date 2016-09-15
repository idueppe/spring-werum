package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@MappedSuperclass
public abstract class AbstractEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @SuppressWarnings("unchecked")
    public AbstractEntity withId(final Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
