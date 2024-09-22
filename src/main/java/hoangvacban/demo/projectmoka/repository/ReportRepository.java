package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
