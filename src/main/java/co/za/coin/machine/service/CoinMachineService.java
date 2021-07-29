package co.za.coin.machine.service;

import co.za.coin.machine.entity.CointEntity;
import co.za.coin.machine.mapper.CoinMachineMapper;
import co.za.coin.machine.repository.CoinRepository;
import org.springframework.stereotype.Component;

@Component
public class CoinMachineService {
    static final int INF = 100000;


    private CoinRepository coinRepository;

    private CoinMachineMapper coinMachineMapper;

    public CoinMachineService(CoinMachineMapper coinMachineMapper, CoinRepository repository) {
        this.coinRepository = repository;
        this.coinMachineMapper = coinMachineMapper;
    }

    public int deno(int change, int[] denominations) {
        int minCoins = minCoins1(denominations, denominations.length, change);
        CointEntity cointEntity = coinMachineMapper.map(change, minCoins);

        //save the results and the change to the db
        CointEntity save = coinRepository.save(cointEntity);


        return minCoins;
    }

    private int minCoins1(int coins[], int m, int V)
    {
        // table[i] will be storing
        // the minimum number of coins
        // required for i value. So
        // table[V] will have result
        int table[] = new int[V + 1];

        // Base case (If given value V is 0)
        table[0] = 0;

        // Initialize all table values as Infinite
        for (int i = 1; i <= V; i++)
            table[i] = Integer.MAX_VALUE;

        // Compute minimum coins required for all
        // values from 1 to V
        for (int i = 1; i <= V; i++)
        {
            // Go through all coins smaller than i
            for (int j = 0; j < m; j++)
                if (coins[j] <= i)
                {
                    int sub_res = table[i - coins[j]];
                    if (sub_res != Integer.MAX_VALUE
                            && sub_res + 1 < table[i])
                        table[i] = sub_res + 1;

                }

        }

        if(table[V]==Integer.MAX_VALUE)
            return -1;

        return table[V];

    }

}
