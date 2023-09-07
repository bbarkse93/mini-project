package shop.mtcoding.blogv2.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DutyRepository extends JpaRepository<Duty, Integer> {

    @Query("SELECT d FROM Duty d WHERE d.dutyName = :dutyName")
    Duty findByDutyName(@Param("dutyName") String duty);
}
