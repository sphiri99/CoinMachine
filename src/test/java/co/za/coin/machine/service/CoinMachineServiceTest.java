package co.za.coin.machine.service;

import co.za.coin.machine.entity.CointEntity;
import co.za.coin.machine.mapper.CoinMachineMapper;
import co.za.coin.machine.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CoinMachineServiceTest {

    @Mock
    private CoinMachineMapper coinMachineMapper;

    @Mock
    private CoinRepository coinRepository;

    private CoinMachineService coinMachineService;

    @BeforeEach
    public void setUp() {
        coinMachineService = new CoinMachineService(coinMachineMapper, coinRepository);

    }

    @Test
    public void testCoinChange() {
        int chnage = 11;
        int denominations [] =  {9, 6, 5, 1};

        when(coinMachineMapper.map(anyInt(),anyInt())).thenReturn(buildCointEntity());
        when(coinRepository.save(any(CointEntity.class))).thenReturn(buildCointEntity());
        int deno = coinMachineService.deno(chnage, denominations);

        assertNotNull(deno);

        assertEquals(2,deno);

        verify(coinMachineMapper).map(anyInt(),anyInt());
        verify(coinRepository).save(any(CointEntity.class));

    }

    @Test
    public void testCoinChangeFailure() {
        int chnage = 11;
        int denominations [] =  {9, 6, 5, 1};
        when(coinMachineMapper.map(anyInt(),anyInt())).thenReturn(buildCointEntity());
        when(coinRepository.save(any())).thenReturn(buildCointEntity());

        int deno = coinMachineService.deno(chnage, denominations);

        assertNotNull(deno);

        assertNotEquals(3, deno);

        verify(coinMachineMapper).map(anyInt(),anyInt());
        verify(coinRepository).save(any());

    }

    public CointEntity buildCointEntity() {
        return  new CointEntity(11, 2);
    }

}