package unlp.info.bd2.repositories;

import java.util.List;

import unlp.info.bd2.model.Stop;

public interface StopRepository extends BaseRepository<Stop> {

    public List<Stop> getStopByName(String name);
}
