package kr.hanisoft.easybank.repository;

import kr.hanisoft.easybank.model.Notice;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long>
{

	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();

}