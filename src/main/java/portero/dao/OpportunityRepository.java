package portero.dao;


import org.springframework.data.repository.CrudRepository;
import portero.domain.Opportunity;

import java.util.List;

public interface OpportunityRepository extends CrudRepository<Opportunity, String> {

    List<Opportunity> findByActive(boolean status);
}
