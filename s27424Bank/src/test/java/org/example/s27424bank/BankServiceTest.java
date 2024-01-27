package org.example.s27424bank;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BankServiceTest {
    @Mock
    private ClientStorage clientStorage;
    @InjectMocks
    private BankService bankService;
    @Test
    void clientreate(){
        Client client = bankService.clientcreate(123);
        assertThat(client.getBalans()).isEqualTo(123);
        assertThat(client.getId()).isEqualTo(1);
        assertThat(client).isNotNull();
        verify(clientStorage, times(1)).addClient(client);
    }
    @Test
    void clientnocreateminusbalans(){
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->
                        bankService.clientcreate(-1))
                .withMessage("balans jest minusowy, Client: DECLINED");

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
    void nomoneyformakeprzelew(){
        Client client = new Client(1,32);
        when(clientStorage.getClientList()).thenReturn(List.of(client));
        String wynik= bankService.przelew(1,40);
        assertThat(wynik).isNotNull();
        assertThat(wynik).isEqualTo("Status Przelewu: DECLINED Balans klienta: 32.0 Nie stać nas na przelew,Nie odjęto balansu");
        verify(clientStorage, times(1)).getClientList();

    }
    @Test
    void przelethrowexceptionwbcsnoclient(){
        when(clientStorage.getClientList()).thenReturn(List.of());
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->
                        bankService.przelew(2,32))
                .withMessage("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
        verify(clientStorage, times(1)).getClientList();
    }
    @Test
    void przelewshouldthrowexceptionbcstransferonminus(){
        Client client = new Client(1,32);
        when(clientStorage.getClientList()).thenReturn(List.of(client));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->
                        bankService.przelew(3,-23))
                .withMessage("nie możesz przelac minusowej kwoty, lub 0 kwoty Status Przelewu: DECLINED");
        verify(clientStorage, times(0)).getClientList();
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
    void wplataexceptionbcsnoclient(){
        when(clientStorage.getClientList()).thenReturn(List.of());
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->
                        bankService.wplata(1,32))
                .withMessage("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
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
    @Test
    void dataFromClientthrowexception(){
        when(clientStorage.getClientList()).thenReturn(List.of());
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->
                        bankService.dataFromClient(3))
                .withMessage("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
        verify(clientStorage, times(1)).getClientList();
    }

}
