package org.example.s27424bank;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BankService {
    private int id=0;
    private final ClientStorage clientStorages;

    public BankService(ClientStorage clientStorages) {
        this.clientStorages = clientStorages;
    }

    public Client clientcreate(double balans) {
        if (balans < 0) {
            throw new RuntimeException("balans jest minusowy,"+" Client: "+Type.DECLINED);
        }
        Client client = new Client(++id, balans);
        clientStorages.addClient(client);
        System.out.println("Dodano klienta: "+client);
        return client;
    }

    public String przelew(int id, double transfer) {

        if (transfer <= 0) {
            throw new RuntimeException("nie możesz przelac minusowej kwoty, lub 0 kwoty Status Przelewu: "+Type.DECLINED);
        }
        List<Client> clientList=clientStorages.getClientList();
        for(Client client : clientList){
            if(client.getId()==id){
                if(client.getBalans()<transfer){
                    String wynik="Status Przelewu: "+Type.DECLINED+" Balans klienta: "+client.getBalans()+" Nie stać nas na przelew,Nie odjęto balansu";
                    return wynik;
                }
                client.setBalans(client.getBalans()-transfer);
                String wynik="Status Przelewu: "+Type.ACCEPTED+" Dodano: "+transfer+" Balans klienta: "+client.getBalans();
                return wynik;
            }

        }
        throw new RuntimeException("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
    }
    public String wplata(int id ,double transfer){
        List<Client> clientList=clientStorages.getClientList();
        for(Client client: clientList){
            if(client.getId()==id) {
                client.setBalans(client.getBalans()+transfer);
                String wynik= "Status Wpłaty: "+Type.ACCEPTED+" Wpłacono: "+transfer+" Balans klienta: "+client.getBalans();
                System.out.println(wynik);
                return wynik;
            }
        }
        throw new RuntimeException("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
    }
    public Client dataFromClient(int id){
        List<Client> clientList=clientStorages.getClientList();
        for(Client client: clientList) {
            if(client.getId()==id){
                System.out.println(client);
                return client;
            }
        }
        throw new RuntimeException("Nie ma takiego klienta Status:"+Type.NO_CLIENT);
    }

}
