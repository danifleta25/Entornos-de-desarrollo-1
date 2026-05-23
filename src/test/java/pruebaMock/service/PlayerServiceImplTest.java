package pruebaMock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pruebaMock.persistance.entity.DataProvider;
import pruebaMock.persistance.entity.Player;
import pruebaMock.persistance.entity.repository.PlayerRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    PlayerRepositoryImpl playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    @Test
    public void testFindAll(){


        //When
        when(playerRepository.findAll()).thenReturn(DataProvider.playerListMock());
        List<Player> resultado = playerService.findAll();

        //Then
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Lionel Messi", resultado.get(0).getName());
        assertEquals("Miami", resultado.get(0).getTeam());
        verify(playerRepository).findAll();
    }

    @Test
    public void testFindById(){

        Player messi = DataProvider.playerMock();

        when(playerRepository.findById(1L)).thenReturn(messi);

        Player resultado = playerService.findById(1L);

        assertEquals(DataProvider.playerMock().getId(), resultado.getId());
        assertEquals(DataProvider.playerMock().getName(), resultado.getName());
        assertEquals(DataProvider.playerMock().getTeam(), resultado.getTeam());

        verify(playerRepository).findById(1L);
    }

    @Test
    public void testSave(){

        Player newPlayer = DataProvider.newPlayerMock();

        playerService.save(newPlayer);

        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(playerArgumentCaptor.capture()); // Capturamos el parametro que se envia al .save

        assertEquals(6L, playerArgumentCaptor.getValue().getId());
        assertEquals("Josu", playerArgumentCaptor.getValue().getName());
        assertEquals("Getafe", playerArgumentCaptor.getValue().getTeam());
    }

    @Test
    public void testDeleteById(){
        Long id = DataProvider.playerMock().getId();

        playerService.deleteById(id);

        verify(playerRepository).deleteById(id);

        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(playerRepository).deleteById(longArgumentCaptor.capture());

        assertEquals(id, longArgumentCaptor.getValue());
        assertFalse(DataProvider.playerListMock().contains(DataProvider.playerMock()));


    }

}