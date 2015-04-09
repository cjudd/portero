package portero.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import portero.domain.Opportunity;

import java.util.List;

@Repository
public interface OpportunityRepository extends CrudRepository<Opportunity, String> {

    List<Opportunity> findByActive(boolean status);
}
