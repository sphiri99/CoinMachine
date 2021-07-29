package co.za.coin.machine.mapper;

import co.za.coin.machine.entity.CointEntity;
import org.springframework.stereotype.Component;

@Component
public class CoinMachineMapper {
    public CointEntity map(int change, int denominations) {
     return new CointEntity(change, denominations);
    }
}
