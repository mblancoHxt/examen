package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByUsernameEquals(String username);

    Client findByIdEquals(int id);

    List<Client> findByListBuy_ListDetails_ProductEquals(Product product);

    List<Client> findByStatusGreaterThan(int status);





















}
