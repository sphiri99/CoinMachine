package co.za.coin.machine.repository;

import co.za.coin.machine.entity.CointEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends PagingAndSortingRepository<CointEntity,Long> {

    @Override
    <S extends CointEntity> S save(S s);

    CointEntity findByChange(int change);

}
