package org.example.s27424bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S27424BankApplication  {
	private final ClientStorage clientStorage;
	private final BankService bankService;

	public S27424BankApplication(ClientStorage clientStorage, BankService bankService) {
		this.clientStorage = clientStorage;
		this.bankService = bankService;
	}

	public static void main(String[] args) {
		SpringApplication.run(S27424BankApplication.class, args);
	}
	public void exec(){
		Client client1 = bankService.clientcreate(500);
		bankService.przelew(1,600);
		bankService.wplata(1,100);
		bankService.przelew(1,600);
		bankService.dataFromClient(1);
		System.out.println(client1.getBalans());


		Client client2 = bankService.clientcreate(110);
		bankService.przelew(2,100);
		bankService.wplata(2,2000);
		bankService.dataFromClient(2);
		System.out.println(client2.getBalans());
		System.out.println(clientStorage.getClientList());
	}

}
