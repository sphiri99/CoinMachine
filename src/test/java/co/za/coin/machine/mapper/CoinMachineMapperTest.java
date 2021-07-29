package co.za.coin.machine.mapper;

import co.za.coin.machine.entity.CointEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoinMachineMapperTest {

    private CoinMachineMapper coinMachineMapper;

    @BeforeEach
    public void setUp() {
        coinMachineMapper = new CoinMachineMapper();
    }

    @Test
    public void testMap() {
        int  denoiminations = 2;
        int chnage = 11;
        CointEntity map = coinMachineMapper.map(chnage, denoiminations);
        assertNotNull(map);

        assertEquals(11, map.getChange());
        assertEquals(2, map.getDenominations());
    }

}