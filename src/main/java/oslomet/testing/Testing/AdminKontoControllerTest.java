package oslomet.testing.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminKontoControllerTest {

    @InjectMocks
    private AdminKontoController adminKontoController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sikkerhet;

    @Test
    public void hentAlleKonti_OK() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentAlleKonti()).thenReturn(kontoer);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertEquals(kontoer, resultat);
    }

    @Test
    public void hentAlleKonti_FEIL() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentAlleKonti()).thenReturn(null);

        List<Konto> resultat = adminKontoController.hentAlleKonti();

        assertNotEquals(kontoer, resultat);
    }

    @Test
    public void registrerKonto_OK() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerKonto(any(Konto.class))).thenReturn("OK");

        String resultat = adminKontoController.registrerKonto(a);

        assertEquals("OK", resultat);
    }

    @Test
    public void registrerKonto_FEIL() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerKonto(any(Konto.class))).thenReturn("Feil");

        String resultat = adminKontoController.registrerKonto(a);

        assertEquals("Feil", resultat);
    }

    @Test
    public void endreKonto_OK() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKonto(any(Konto.class))).thenReturn("OK");

        String resultat = adminKontoController.endreKonto(a);

        assertEquals("OK", resultat);
    }

    @Test
    public void endreKonto_FEIL() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKonto(any(Konto.class))).thenReturn("Feil");

        String resultat = adminKontoController.endreKonto(a);

        assertEquals("Feil", resultat);
    }

    @Test
    public void slettKonto_OK() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.slettKonto(anyString())).thenReturn("OK");

        String resultat = adminKontoController.slettKonto("");

        assertEquals("OK", resultat);
    }

    @Test
    public void slettKonto_FEIL() {
        Konto a = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.slettKonto(anyString())).thenReturn("Feil");

        String resultat = adminKontoController.slettKonto("");

        assertEquals("Feil", resultat);
    }


}
