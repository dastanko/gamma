package project.gamma.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: sergey
 * Date: 04.02.11
 */
@Entity
public class Service extends AbstractService implements WithId {
    private int id;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return super.getName();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int getPrice() {
        return super.getPrice();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int getDuration() {
        return super.getDuration();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
