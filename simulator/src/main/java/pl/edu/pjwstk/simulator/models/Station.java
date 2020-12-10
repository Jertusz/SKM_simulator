package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;

@Entity
@Table(name = "stations")
public class Station implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public Long getId() {
        return id;
    }
}