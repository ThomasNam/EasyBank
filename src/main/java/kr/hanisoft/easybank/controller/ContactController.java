package kr.hanisoft.easybank.controller;

import kr.hanisoft.easybank.model.Contact;
import kr.hanisoft.easybank.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ContactController
{
	private final ContactRepository contactRepository;

	@PostMapping("/contact")
	public Contact saveContactInquiryDetails (@RequestBody Contact contact)
	{
		contact.setContactId (getServiceReqNumber ());
		contact.setCreateDt (new Date (System.currentTimeMillis ()));
		return contactRepository.save (contact);
	}

	public String getServiceReqNumber ()
	{
		Random random = new Random ();
		int ranNum = random.nextInt (999999999 - 9999) + 9999;
		return "SR" + ranNum;
	}
}
