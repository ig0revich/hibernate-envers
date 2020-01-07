package just4fun.repo;

import just4fun.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodEventsRepository extends CrudRepository<GoodEvent, Long> {

}
