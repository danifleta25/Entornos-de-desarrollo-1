package pruebaMock.persistance.entity.repository;

import pruebaMock.persistance.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements IPlayerRepository{

    private List<Player> playerDatabase = new ArrayList<>();


    @Override
    public List<Player> findAll() {
        System.out.println("--> Metodo findAll real!!");
        return this.playerDatabase;
    }

    @Override
    public Player findById(Long id) {
        System.out.println("--> Metodo findById real!!");

        return this.playerDatabase.stream()
                .filter(player -> player.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void save(Player player) {
        System.out.println("--> Metodo save real!!");
        this.playerDatabase.add(player);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("--> Metodo deleteById real!!");
        this.playerDatabase.removeIf(player -> player.getId().equals(id));
    }
}
