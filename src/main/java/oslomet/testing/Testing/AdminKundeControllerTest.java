package oslomet.testing.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminKundeControllerTest {

    @InjectMocks
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sikkerhet;

    @Test
    public void hentAlle_OK() {
        List<Kunde> kunder = new ArrayList<>();
        Kunde a = new Kunde();
        kunder.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentAlleKunder()).thenReturn(kunder);

        List<Kunde> resultat = adminKundeController.hentAlle();

        assertEquals(kunder, resultat);
    }

    @Test
    public void hentAlle_FEIL() {
        List<Kunde> kunder = new ArrayList<>();
        Kunde a = new Kunde();
        kunder.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentAlleKunder()).thenReturn(null);

        List<Kunde> resultat = adminKundeController.hentAlle();

        assertNotEquals(kunder, resultat);
    }

    @Test
    public void lagreKunde_OK() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        String resultat = adminKundeController.lagreKunde(kunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void lagreKunde_FEIL() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerKunde(any(Kunde.class))).thenReturn("Feil");

        String resultat = adminKundeController.lagreKunde(kunde);

        assertEquals("Feil", resultat);
    }

    @Test
    public void endre_OK() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String resultat = adminKundeController.endre(kunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void endre_FEIL() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Feil");

        String resultat = adminKundeController.endre(kunde);

        assertEquals("Feil", resultat);
    }

    @Test
    public void slett_OK() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.slettKunde(anyString())).thenReturn("OK");

        String resultat = adminKundeController.slett("");

        assertEquals("OK", resultat);
    }

    @Test
    public void slett_FEIL() {
        Kunde kunde = new Kunde();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.slettKunde(anyString())).thenReturn("Feil");

        String resultat = adminKundeController.slett("");

        assertEquals("Feil", resultat);
    }

}
