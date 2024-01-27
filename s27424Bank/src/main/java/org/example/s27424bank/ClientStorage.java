package org.example.s27424bank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientStorage {
    List<Client> clientList=new ArrayList<>();
    public void addClient(Client client){
        clientList.add(client);
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
