package springframework.weatherapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springframework.weatherapplication.model.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "SELECT temperature FROM cities where name=:name order by name ASC ",nativeQuery = true)
    List<Integer> findAllData (@Param("name") String name);

    @Query(value = "SELECT date FROM cities where name=:name order by name ASC ",nativeQuery = true)
    List<String> findAllLabels(@Param("name") String name);

}
