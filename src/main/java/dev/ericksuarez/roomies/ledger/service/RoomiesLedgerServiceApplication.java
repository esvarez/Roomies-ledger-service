package dev.ericksuarez.roomies.ledger.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RoomiesLedgerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomiesLedgerServiceApplication.class, args);
	}

}
