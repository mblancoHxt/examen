package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BuyRepository extends JpaRepository<Buy, Integer> {
    Buy findByIdEquals(Integer id);

    List<Buy> findByListDetails_Product_IdEquals(Integer id);

    List<Buy> findByListDetails_Product_IdEqualsAndClient_StatusGreaterThanAndSeller_StatusGreaterThan(Integer id, int status, int status1);

    List<Buy> findByClient_IdEquals(int id);

    List<Buy> findByClient_IdEqualsAndClient_StatusGreaterThanAndClient_StatusGreaterThan(int id, int status, int status1);

    @Query("select b from Buy b where b.client.status > 0 and b.seller.status > 0")
    List<Buy> findAllBuys();


    





}
