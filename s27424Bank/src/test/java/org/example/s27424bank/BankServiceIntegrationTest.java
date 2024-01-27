package org.example.s27424bank;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BankServiceIntegrationTest {
    @Autowired
    private BankService bankService;

    @MockBean
    private ClientStorage clientStorage;

    @Test
    void clientreate(){
        Client client = bankService.clientcreate(123);
        assertThat(client.getBalans()).isEqualTo(123);
        assertThat(client.getId()).isEqualTo(1);
        assertThat(client).isNotNull();
        verify(clientStorage, times(1)).addClient(client);
    }
    @Test
    void makeprzelew(){
        Client client = new Client(1,32);
        when(clientStorage.getClientList()).thenReturn(List.of(client));
        String wynik= bankService.przelew(1,20);
        assertThat(wynik).isNotNull();
        assertThat(wynik).isEqualTo("Status Przelewu: ACCEPTED Dodano: 20.0 Balans klienta: 12.0");
        verify(clientStorage, times(1)).getClientList();

    }
    @Test
    void makewplata(){
        Client client = new Client(1,32);
        when(clientStorage.getClientList()).thenReturn(List.of(client));
        String wynik = bankService.wplata(1,322);
        assertThat(client.getBalans()).isEqualTo(354);
        assertThat(wynik).isEqualTo("Status Wpłaty: ACCEPTED Wpłacono: 322.0 Balans klienta: 354.0");
        verify(clientStorage, times(1)).getClientList();
    }
    @Test
    void dataFromClient(){
        Client client = new Client(1,32);
        when(clientStorage.getClientList()).thenReturn(List.of(client));
        Client client1=bankService.dataFromClient(1);
        assertThat(client1).isNotNull();
        assertThat(client).isEqualTo(client1);
        verify(clientStorage, times(1)).getClientList();
    }
}
