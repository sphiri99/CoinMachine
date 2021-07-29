package co.za.coin.machine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CointEntity {
    //defining id as column name

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private int change;
    @Column
    private int denominations;

    public CointEntity(int change, int denominations) {
        this.change = change;
        this.denominations = denominations;
    }
}
