package kr.hanisoft.easybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CardsController
{
	@GetMapping("/myCards")
	public String myCards()
	{
		return "myCard";
	}
}
