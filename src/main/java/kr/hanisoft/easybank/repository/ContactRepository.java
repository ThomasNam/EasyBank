package kr.hanisoft.easybank.repository;

import kr.hanisoft.easybank.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String>
{


}