package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "passengers")
public class Passenger implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @OneToOne(fetch = FetchType.EAGER)
    Station targetStation;

    public Passenger() {

        firstName = String.valueOf(FirstName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 100)));
        lastName = String.valueOf(LastName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 94)));
//        targetStation = Station.VALUES.get(ThreadLocalRandom.current().nextInt(0, 14));
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

}
