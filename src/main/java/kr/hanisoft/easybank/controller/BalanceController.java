package kr.hanisoft.easybank.controller;

import kr.hanisoft.easybank.model.AccountTransactions;
import kr.hanisoft.easybank.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class BalanceController
{
	private final AccountTransactionsRepository accountTransactionsRepository;

	@GetMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails (@RequestParam long id)
	{
		List<AccountTransactions> accountTransactions = accountTransactionsRepository.
				findByCustomerIdOrderByTransactionDtDesc (id);
		if (accountTransactions != null)
		{
			return accountTransactions;
		}
		else
		{
			return null;
		}
	}
}
