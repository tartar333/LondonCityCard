package com.mcann.utility;

import com.mcann.service.*;
import com.mcann.utility.enums.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements ApplicationRunner {
	private final CardService cardService;
	private final UserService userService;
	private final StationService stationService;
	private final LineService lineService;
	
	public DataGenerator(CardService cardService, UserService userService, StationService stationService, LineService lineService) {
		this.cardService = cardService;
		this.userService = userService;
		this.stationService = stationService;
		this.lineService = lineService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		addCard();
//		addUser();
//		balanceLoadCard();
//		balanceDeductionCard();
//		addStation();
//		addLine();
		firstUsageBalanceDeductionCard();
		
	}
	public String addCard() {
		cardService.addCard(1L,"1234 3214 9654 8523", 50.0, LocalDate.of(2025, 10, 10), "050", CardType.STANDARD);
		cardService.addCard(1L,"3214 3214 8546 8523", 70.0, LocalDate.of(2025, 8, 8), "052", CardType.STUDENT);
		cardService.addCard(2L,"8526 3214 3256 8523", 90.0, LocalDate.of(2025, 5, 5), "054", CardType.ELDERLY);
		return "Card bilgileri eklendi.";
	}
	
	public String addUser() {
		userService.addUser("Mehmet", "Tufan", "m.tufan@gmail.com", "123456", "05225473366", "Adana", "mtufan", LocalDate.of(1992,10,10));
		userService.addUser("Ozkan","Sargin","o.sargin@gmail.com","123456","05356998877","Karadag","osargin",LocalDate.of(1991,02,14));
		userService.addUser("Mehmet Can","Karahan","mcan@gmail.com","123456","05368254788","Istanbul","mcan",LocalDate.of(2000,06,19));
		return "Kullanicilar eklendi";
	}
	
	public String balanceLoadCard() throws Exception {
		cardService.balanceLoadCard(1L,100d,PaymentType.CARD);
		return "Para Yuklendi";
	}
	public String firstUsageBalanceDeductionCard() throws Exception {
		cardService.firstUsageBalanceDeductionCard(2L,PaymentType.CARD,TransitionType.INITIAL_USAGE);
		return "Bakiyenizden para harcandı";
	}
	
	public String addStation(){
		stationService.addStation("BeylikduzuSonDurak", "B-1", StationType.METROBUS,"Beylikduzu");
		stationService.addStation("Avcilar","A-3", StationType.METROBUS,"Avcilar");
		return "Metrobus duraklari eklendi";
	}
	
	public String addLine(){
		lineService.addLine("BeylikduzuSonDurak - Avcilar", "34A", VehicleType.METROBUS,1L,2L);
		return "Metrobus duraklari eklendi";
	}
}