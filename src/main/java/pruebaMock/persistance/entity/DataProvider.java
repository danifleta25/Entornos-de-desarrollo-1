package pruebaMock.persistance.entity;

import java.util.List;

public class DataProvider {



    public static List<Player> playerListMock(){

        System.out.println(" --> Obteniendo listado de players Mock");

        return List.of(
                new Player(1L, "Lionel Messi", "Miami", "Delantero"),
                new Player(2L, "Ronaldo", "Al Nassr", "Delantero"),
                new Player(3L, "Neymar", "Paris St Germain", "Delantero")
        );
    }

    public static Player playerMock(){
        return new Player(1L, "Lionel Messi", "Miami", "Delantero");
    }

    public static Player newPlayerMock(){
        return new Player(6L, "Josu", "Getafe", "Central");
    }

}
