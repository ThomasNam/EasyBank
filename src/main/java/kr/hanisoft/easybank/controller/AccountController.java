package kr.hanisoft.easybank.controller;

import kr.hanisoft.easybank.model.Accounts;
import kr.hanisoft.easybank.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AccountController
{
	private final AccountsRepository accountsRepository;

	@GetMapping("/myAccount")
	public Accounts getAccountDetails (@RequestParam long id)
	{
		Accounts accounts = accountsRepository.findByCustomerId (id);
		if (accounts != null)
		{
			return accounts;
		}
		else
		{
			return null;
		}
	}
}
