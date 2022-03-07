package oslomet.testing.Testing;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;
import oslomet.testing.Testing.tools.SessionBeforeCall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankControllerTest {

    @InjectMocks
    private BankController bankController;

    @Mock
    private BankRepository repository;

    @Mock
    private Sikkerhet sikkerhet;

    @Mock
    MockHttpSession session;


    @Test
    public void hentKundeInfo_OK() {
        Kunde kunde = new Kunde("23030079582", "Martin",
                "Bekkos", "ABC 123", "1233", "Oslo",
                "12345678", "HeiHei");

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentKundeInfo(anyString())).thenReturn(kunde);

        Kunde resultat = bankController.hentKundeInfo();

        assertEquals(kunde, resultat);

    }

    @Test
    public void hentKundeInfo_FEIL() {
        Kunde kunde = new Kunde("23030079582", "Martin",
                "Bekkos", "ABC 123", "1233", "Oslo",
                "12345678", "HeiHei");

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentKundeInfo(anyString())).thenReturn(null);

        Kunde resultat = bankController.hentKundeInfo();

        assertNotEquals(kunde, resultat);

    }

    @Test
    public void endreKundeInfo_OK() {
        Kunde kunde = new Kunde("23030079582", "Martin",
                "Bekkos", "ABC 123", "1233", "Oslo",
                "12345678", "HeiHei");

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        String resultat = bankController.endre(kunde);

        assertEquals("OK", resultat);
    }

    @Test
    public void endreKundeInfo_FEIL() {
        Kunde kunde = new Kunde("23030079582", "Martin",
                "Bekkos", "ABC 123", "1233", "Oslo",
                "12345678", "HeiHei");

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Feil");

        String resultat = bankController.endre(kunde);

        assertEquals("Feil", resultat);
    }

    @Test
    public void hentTransaksjoner_OK() {
        Konto konto = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(konto);

        Konto resultat = bankController.hentTransaksjoner("", "", "");

        assertEquals(konto, resultat);
    }

    @Test
    public void hentTransaksjoner_FEIL() {
        Konto konto = new Konto();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(null);

        Konto resultat = bankController.hentTransaksjoner("", "", "");

        assertNotEquals(konto, resultat);
    }

    @Test
    public void hentKonti_OK() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentKonti(anyString())).thenReturn(kontoer);

        List<Konto> resultat = bankController.hentKonti();

        assertEquals(kontoer, resultat);
    }

    @Test
    public void hentKonti_FEIL() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentKonti(anyString())).thenReturn(null);

        List<Konto> resultat = bankController.hentKonti();

        assertNotEquals(kontoer, resultat);
    }

    @Test
    public void hentSaldi_OK() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentSaldi(anyString())).thenReturn(kontoer);

        List<Konto> resultat = bankController.hentSaldi();

        assertEquals(kontoer, resultat);
    }

    @Test
    public void hentSaldi_FEIL() {
        List<Konto> kontoer = new ArrayList<>();
        Konto a = new Konto();
        kontoer.add(a);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentSaldi(anyString())).thenReturn(null);

        List<Konto> resultat = bankController.hentSaldi();

        assertNotEquals(kontoer, resultat);
    }

    @Test
    public void registrerBetaling_OK() {
        Transaksjon t = new Transaksjon();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerBetaling(any(Transaksjon.class))).thenReturn("OK");

        String resultat = bankController.registrerBetaling(t);

        assertEquals("OK", resultat);
    }

    @Test
    public void registrerBetaling_FEIL() {
        Transaksjon t = new Transaksjon();

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.registrerBetaling(any(Transaksjon.class))).thenReturn("Feil");

        String resultat = bankController.registrerBetaling(t);

        assertEquals("Feil", resultat);
    }

    @Test
    public void hentBetalinger_OK() {
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon t = new Transaksjon();
        transaksjoner.add(t);


        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentBetalinger(anyString())).thenReturn(transaksjoner);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertEquals(transaksjoner, resultat);
    }

    @Test
    public void hentBetalinger_FEIL() {
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon t = new Transaksjon();
        transaksjoner.add(t);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.hentBetalinger(anyString())).thenReturn(null);

        List<Transaksjon> resultat = bankController.hentBetalinger();

        assertNotEquals(transaksjoner, resultat);
    }

    @Test
    public void utforBetaling_OK() {
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon t = new Transaksjon();
        transaksjoner.add(t);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.utforBetaling(anyInt())).thenReturn("OK");
        when(repository.hentBetalinger(anyString())).thenReturn(transaksjoner);

        List<Transaksjon> resultat = bankController.utforBetaling(1);

        assertEquals(transaksjoner, resultat);
    }

    @Test
    public void utforBetaling_FEIL() {
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon t = new Transaksjon();
        transaksjoner.add(t);

        when(sikkerhet.loggetInn()).thenReturn("23030079582");
        when(repository.utforBetaling(anyInt())).thenReturn("OK");
        when(repository.hentBetalinger(anyString())).thenReturn(null);

        List<Transaksjon> resultat = bankController.utforBetaling(1);

        assertNotEquals(transaksjoner, resultat);
    }

}
