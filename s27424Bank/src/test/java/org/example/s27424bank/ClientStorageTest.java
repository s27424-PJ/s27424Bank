package org.example.s27424bank;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class ClientStorageTest {
    private ClientStorage clientStorage = new ClientStorage();
    @Test
    void shouldHaveEntriesInStorage(){
        clientStorage.addClient(new Client(1,1));
        clientStorage.addClient(new Client(2,2));
        List<Client> clientList= clientStorage.getClientList();
        assertThat(clientList).hasSize(2);
    }
    @Test
    void shouldBeenEmpty(){

        List<Client> clientList= clientStorage.getClientList();
        assertThat(clientList).hasSize(0);
    }
}
