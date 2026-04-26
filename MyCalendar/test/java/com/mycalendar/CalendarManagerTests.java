package com.mycalendar;

import com.mycalendar.datas.*;

import com.mycalendar.types.*;

import com.mycalendar.types.categories.EventPeriodique;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarManagerTests {

    /**
     * Calendrier vide (aucun évènement)
     */
    private static CalendarManager EMPTY_CALENDAR_MANAGER;

    /**
     * Calendrier contenant 1 seul évènement
     */
    private static CalendarManager CALENDAR_MANAGER_WITH_ONE_EVENT;

    /**
     * Calendrier contenant plusieurs évènements
     */
    private static CalendarManager CALENDAR_MANAGER_WITH_MANY_EVENT;

    /**
     * Calendrier contenant un évènement de type PERIODIQUE
     */
    private static CalendarManager CALENDAR_MANAGER_WITH_PERIODIC_EVENT;

    /**
     * Evènements de type périodique
     */
    private static Event PERIODIC_EVENT_ONE;

    /**
     * Evènements de type non périodique
     */
    private static Event NON_PERIODIC_EVENT_ONE;
    private static Event NON_PERIODIC_EVENT_TWO;



    /**
     * Attributs nécessaires pour tester la sortie du println()
     */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }



    /**
     * Initialisation des calendriers et évènements de test
     */
    @BeforeAll
    static void beforeAll() {

        initEvents();
        initCalendars();

    }



    /**
     * Initialise les évènements de tests
     */
    private static void initEvents() {
        PERIODIC_EVENT_ONE = new Anniversaire(
                new Titre("Evènement 1"),
                new Personne("Noah"),
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 0))
        );

        var participants = new ArrayList<Personne>(
                List.of(new Personne("Mr X"), new Personne("Loup"), new Personne("Elias")));

        NON_PERIODIC_EVENT_ONE = new Reunion(
                new Titre("Evènement 1"),
                new Personne("Loup"),
                new DateEvenement(2, 1, 2026, new HeureDebut(15, 0)),
                new Duree(90),
                new Lieu("Paris"),
                participants
        );

        NON_PERIODIC_EVENT_TWO = new RdvPersonnel(
                new Titre("Evènement 2"),
                new Personne("Mr X"),
                new DateEvenement(5, 1, 2026, new HeureDebut(15, 0)),
                new Duree(90)
        );
    }

    /**
     * Initialise les calendriers de tests
     */
    private static void initCalendars() {

        EMPTY_CALENDAR_MANAGER = new CalendarManager();
        CALENDAR_MANAGER_WITH_ONE_EVENT = new CalendarManager();
        CALENDAR_MANAGER_WITH_MANY_EVENT = new CalendarManager();
        CALENDAR_MANAGER_WITH_PERIODIC_EVENT = new CalendarManager();

        var participants = new ArrayList<>(List.of(new Personne("Noah"), new Personne("Loup"), new Personne("Elias")));

        CALENDAR_MANAGER_WITH_ONE_EVENT.ajouterEvent(
                new Reunion(
                        new Titre("Réunion test"),
                        new Personne("Noah"),
                        new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)),
                        new Duree(60),
                        new Lieu("Nancy"),
                        participants
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Reunion(
                        new Titre("Réunion test"),
                        new Personne("Noah"),
                        new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)),
                        new Duree(60),
                        new Lieu("Nancy"),
                        participants
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new RdvPersonnel(
                        new Titre("Réunion test 2"),
                        new Personne("Loup"),
                        new DateEvenement(2, 1, 2026, new HeureDebut(10, 0)),
                        new Duree(60)
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Anniversaire(
                        new Titre("Réunion test 3"),
                        new Personne("Mr X"),
                        new DateEvenement(5, 1, 2026, new HeureDebut(21, 0))
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Anniversaire(
                        new Titre("Anniversaire de Noah"),
                        new Personne("Noah"),
                        new DateEvenement(24, 12, 2026, new HeureDebut(0, 0))
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Entrainement(
                        new Titre("Anniversaire de Noah"),
                        new Personne("Noah"),
                        new DateEvenement(24, 12, 2026, new HeureDebut(0, 0)),
                        new Duree(30),
                        new Frequence(2)
                )
        );

        CALENDAR_MANAGER_WITH_PERIODIC_EVENT.ajouterEvent(
                new Anniversaire(
                        new Titre("Réunion test Périodique"),
                        new Personne("Mr X"),
                        new DateEvenement(1, 1, 2026, new HeureDebut(15, 0))
                )
        );
    }



    @Test
    @DisplayName("Création d'un nouveau calendrier")
    public void testCreateCalendar() {

        var calendarManager = new CalendarManager();

        var listEvents = calendarManager.events;

        assertEquals(0, listEvents.size(), "La liste doit être vide");
    }

    @Test
    @DisplayName("Ajout d'un nouvel évènement")
    public void testAddEvent() {

        var calendarManager = new CalendarManager();
        var participants = new ArrayList<>(List.of(new Personne("Noah"), new Personne("Loup"), new Personne("Elias")));

        calendarManager.ajouterEvent(
                new Reunion(
                        new Titre("Réunion test"),
                        new Personne("Noah"),
                        new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)),
                        new Duree(60),
                        new Lieu("Nancy"),
                        participants
                )
        );
        var listEvents = calendarManager.events;

        assertEquals(1, listEvents.size(), "La liste doit contenir 1 seul évènement");
    }

    @Test
    @DisplayName("Récupération d'aucun évènement dans une période donnée")
    public void testGetZeroEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_MANY_EVENT;
        var dateDebut = new DateEvenement(18, 12, 2025, new HeureDebut(15, 0));
        var dateFin = new DateEvenement(11, 12, 2025, new HeureDebut(15, 0));

        var res = calendarManager.eventsDansPeriode(dateDebut.getDate(), dateFin.getDate());

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");
    }

    @Test
    @DisplayName("Récupération d'un évènement non périodique dans une période donnée")
    public void testGetOneNonPeriodicEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_MANY_EVENT;
        var dateDebut = new DateEvenement(1, 1, 2026, new HeureDebut(14, 55));
        var dateFin = new DateEvenement(1, 1, 2026, new HeureDebut(15, 5));

        var res = calendarManager.eventsDansPeriode(dateDebut.getDate(), dateFin.getDate());

        assertEquals(1, res.size(), "La liste doit contenir 1 seul évènement");
        assertEquals(TypeCode.REUNION, res.getFirst().type, "Le type de l'évènement doit être 'REUNION'");
    }

    @Test
    @DisplayName("Récupération d'un évènement périodique dans une période donnée")
    public void testGetOnePeriodicEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;
        var dateDebut = new DateEvenement(1, 1, 2026, new HeureDebut(14, 55));
        var dateFin = new DateEvenement(1, 1, 2026, new HeureDebut(15, 5));

        var res = calendarManager.eventsDansPeriode(dateDebut.getDate(), dateFin.getDate());

        assertEquals(1, res.size(), "La liste doit contenir 1 seul évènement");
        assertEquals(TypeCode.ANNIVERSAIRE, res.getFirst().type, "Le type de l'évènement doit être 'PERIODIQUE'");
    }

    @Test
    @DisplayName("Récupération évènement périodique avec en entrée de la méthode la même date de début et de fin")
    public void testGetPeriodicEventWithSameDateInEntry() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;

        var res = calendarManager.eventsDansPeriode(
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)).getDate(),
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)).getDate()
        );

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");
    }

    @Test
    @DisplayName("Récupération d'un évènement périodique où la date de début se trouve avant la date de début passée " +
            "en paramètre de la méthode")
    public void testGetPeriodicEventWithDateBeforeStartingDate() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;
        var dateDebut = new DateEvenement(1, 1, 2026, new HeureDebut(15, 5));
        var dateFin = new DateEvenement(1, 1, 2026, new HeureDebut(15, 10));

        var res = calendarManager.eventsDansPeriode(dateDebut.getDate(), dateFin.getDate());

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");
    }

    @Test
    @DisplayName("Récupération d'un évènement non périodique où la date de début se trouve avant la date de début " +
            "passée en paramètre de la méthode")
    public void testGetNonPeriodicEventWithDateBeforeStartingDate() {

        var calendarManager = CALENDAR_MANAGER_WITH_ONE_EVENT;
        var dateDebut = new DateEvenement(1, 1, 2026, new HeureDebut(15, 5));
        var dateFin = new DateEvenement(1, 1, 2026, new HeureDebut(15, 10));

        var res = calendarManager.eventsDansPeriode(dateDebut.getDate(), dateFin.getDate());

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");
    }

    @Test
    @DisplayName("Test de conflit avec un évènement périodique et un évènement non périodique")
    public void testConflictWithOnePeriodicEventAndOneNonPeriodicEvent() {
        var calendarManager = EMPTY_CALENDAR_MANAGER;
        var res = calendarManager.conflit(PERIODIC_EVENT_ONE, NON_PERIODIC_EVENT_ONE);
        assertFalse(res, "La méthode doit renvoyer false");
    }

    @Test
    @DisplayName("Test de conflit avec deux évènements non périodique")
    public void testConflictWithTwoNonPeriodicEvent() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var res = calendarManager.conflit(NON_PERIODIC_EVENT_ONE, NON_PERIODIC_EVENT_TWO);

        assertFalse(res, "La méthode doit renvoyer false");
    }

    @Test
    @DisplayName("Test de conflit avec un évènements non périodique et un évènement périodique")
    public void testConflictWithOneNonPeriodicEventAndOnePeriodicEvent() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var res = calendarManager.conflit(NON_PERIODIC_EVENT_ONE, PERIODIC_EVENT_ONE);

        assertFalse(res, "La méthode doit renvoyer false");
    }

    @Test
    @DisplayName("Test de conflit où la date de fin du premier évènement se trouve après la date de début du second")
    public void testConflictWithFirstEndDateAfterSecondStartDate() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;
        var participants = new ArrayList<>(List.of(new Personne("Mr X"), new Personne("Loup"), new Personne("Elias")));

        var event1 = new Reunion(
                new Titre("Evènement 1"),
                new Personne("Loup"),
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)),
                new Duree(90),
                new Lieu("Paris"),
                participants
        );

        var event2 = new Reunion(
                new Titre("Evènement 1"),
                new Personne("Loup"),
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 30)),
                new Duree(60),
                new Lieu("Paris"),
                participants
        );

        var res = calendarManager.conflit(event1, event2);

        assertTrue(res, "La méthode doit renvoyer true");
    }

    @Test
    @DisplayName("Test de conflit où la date de début du premier évènement se trouve après la fin du second")
    public void testConflictWithFirstStartDateAfterSecondEndDate() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;
        var participants = new ArrayList<>(List.of(new Personne("Mr X"), new Personne("Loup"), new Personne("Elias")));

        var event1 = new Reunion(
                new Titre("Evènement 1"),
                new Personne("Loup"),
                new DateEvenement(2, 1, 2026, new HeureDebut(15, 0)),
                new Duree(90),
                new Lieu("Paris"),
                participants
        );

        var event2 = new Reunion(
                new Titre("Evènement 1"),
                new Personne("Loup"),
                new DateEvenement(1, 1, 2026, new HeureDebut(15, 0)),
                new Duree(60),
                new Lieu("Paris"),
                participants
        );

        var res = calendarManager.conflit(event1, event2);

        assertFalse(res, "La méthode doit renvoyer false");
    }

    @Test
    @DisplayName("Affichage d'un calendrier vide")
    public void testDisplayEmptyCalendar() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        calendarManager.afficherEvenements();

        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Affichage d'un calendrier contenant plusieurs évènements")
    public void testDisplayCalendar() {

        var calendarManager = CALENDAR_MANAGER_WITH_MANY_EVENT;
        calendarManager.afficherEvenements();

        var expected = """
        Réunion : Réunion test à Nancy avec Noah, Loup, Elias
        RDV : Réunion test 2 à 2026-01-02T10:00
        Anniversaire de Mr X !
        Anniversaire de Noah !
        Entraînement de Anniversaire de Noah (tous les 2 jours)
        """;

        var actual = outputStreamCaptor.toString()
                .replace("\r\n", "\n")
                .strip();

        assertEquals(expected.strip(), actual);
    }

    @Test
    @DisplayName("Récupération d'un anniversaire l'année suivante")
    public void testGetAnniversaireNextYear() {

        var calendarManager = new CalendarManager();

        Event anniv = new Anniversaire(
                new Titre("Anniversaire Noah"),
                new Personne("Noah"),
                new DateEvenement(24, 12, 2026, new HeureDebut(0, 0))
        );
        calendarManager.ajouterEvent(anniv);

        var debut2027 = LocalDateTime.of(2027, 12, 23, 0, 0);
        var fin2027 = LocalDateTime.of(2027, 12, 25, 0, 0);

        var res = calendarManager.eventsDansPeriode(debut2027, fin2027);

        assertEquals(1, res.size(), "L'anniversaire doit être trouvé en 2027");
    }

    @Test
    @DisplayName("Cas où l'évènement commence AVANT la période demandée")
    public void testGetNonPeriodicEventStartingBeforePeriod() {

        var calendarManager = new CalendarManager();
        var rdv = new RdvPersonnel(
                new Titre("Médecin"),
                new Personne("Noah"),
                new DateEvenement(10, 1, 2026, new HeureDebut(10, 0)),
                new Duree(30)
        );
        calendarManager.ajouterEvent(rdv);

        var debut = new DateEvenement(10, 1, 2026, new HeureDebut(11, 0)).getDate();
        var fin = new DateEvenement(10, 1, 2026, new HeureDebut(12, 0)).getDate();
        var res = calendarManager.eventsDansPeriode(debut, fin);

        assertEquals(0, res.size(), "L'évènement commence avant la période, il ne doit pas être retourné");
    }

    @Test
    @DisplayName("Occurrence annuelle avant le début de la recherche")
    public void testAnniversaireOccurrenceBeforeSearchPeriod() {
        var calendarManager = new CalendarManager();

        var anniv = new Anniversaire(
                new Titre("Anniv Noah"),
                new Personne("Noah"),
                new DateEvenement(5, 1, 2026, new HeureDebut(0, 0))
        );
        calendarManager.ajouterEvent(anniv);


        var debut = new DateEvenement(10, 1, 2026, new HeureDebut(0, 0)).getDate();
        var fin = new DateEvenement(20, 1, 2026, new HeureDebut(0, 0)).getDate();
        var res = calendarManager.eventsDansPeriode(debut, fin);

        assertEquals(0, res.size(), "L'anniversaire est passé, il ne devrait pas être trouvé");
    }

    @Test
    @DisplayName("L'occurrence de l'année suivante est aussi avant le début de la recherche")
    public void testAnniversaireOccurrenceNextYearBeforeSearchPeriod() {

        var anniv = new Anniversaire(
                new Titre("Anniv Noah"),
                new Personne("Noah"),
                new DateEvenement(5, 1, 2026, new HeureDebut(0, 0))
        );

        var debut = new DateEvenement(1, 12, 2029, new HeureDebut(0, 0)).getDate();
        var fin = new DateEvenement(10, 12, 2029, new HeureDebut(0, 0)).getDate();
        var res = anniv.estDansPeriode(debut, fin);

        assertFalse(res, "L'anniversaire de l'année suivante est aussi déjà passé");

    }

    @Test
    @DisplayName("Vérifie qu'une occurrence annuelle est détectée lors d'une recherche sur l'année suivante")
    public void testEventPeriodiqueOccurrenceSuccess() {

        Event anniv = new Anniversaire(
                new Titre("Anniversaire"),
                new Personne("Noah"),
                new DateEvenement(5, 1, 2026, new HeureDebut(0, 0))
        );

        LocalDateTime debut = LocalDateTime.of(2027, 1, 1, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2027, 1, 10, 0, 0);

        assertTrue(anniv.estDansPeriode(debut, fin),
                "L'occurrence de 2027 devrait être détectée par la boucle d'EventPeriodique");
    }

    @Test
    @DisplayName("Vérifie qu'un évènement périodique est ignoré quand la fenêtre de recherche tombe dans le creux entre deux répétitions")
    public void testEntrainementBetweenOccurrences() {
        var sport = new Entrainement(
                new Titre("Tennis"), new Personne("Noah"),
                new DateEvenement(1, 1, 2026, new HeureDebut(10, 0)),
                new Duree(60),
                new Frequence(5)
        );

        LocalDateTime debutRecherche = LocalDateTime.of(2026, 1, 3, 0, 0);
        LocalDateTime finRecherche = LocalDateTime.of(2026, 1, 4, 0, 0);

        assertFalse(sport.estDansPeriode(debutRecherche, finRecherche));
    }

    @Test
    @DisplayName("Vérifie la détection d'une occurrence située après un saut de fréquence)")
    public void testEstDansPeriodeTrueHitAfterJump() {

        var sport = new Entrainement(
                new Titre("Foot"), new Personne("Noah"),
                new DateEvenement(1, 1, 2026, new HeureDebut(10, 0)),
                new Duree(60), new Frequence(2)
        );

        var debutRecherche = LocalDateTime.of(2026, 1, 2, 0, 0);
        var finRecherche = LocalDateTime.of(2026, 1, 4, 0, 0);

        assertTrue(sport.estDansPeriode(debutRecherche, finRecherche));
    }

    @Test
    @DisplayName("Vérifie qu'un conflit est détecté sur une occurrence ultérieure de l'évènement")
    public void testOccupeCreneauTrueHitAfterJump() {

        var cours = new Entrainement(
                new Titre("Yoga"), new Personne("Noah"),
                new DateEvenement(1, 1, 2026, new HeureDebut(10, 0)),
                new Duree(60), new Frequence(5)
        );

        var debutConflit = LocalDateTime.of(2026, 1, 6, 9, 0);
        var finConflit = LocalDateTime.of(2026, 1, 6, 12, 0);

        assertTrue(cours.occupeCreneau(debutConflit, finConflit));
    }

    @Test
    @DisplayName("Vérifie que la suppression par ID fonctionne sans boucle manuelle")
    public void testSuppressionDeclarative() {
        var manager = new CalendarManager();

        var ev1 = new RdvPersonnel(
                new Titre("RDV 1"),
                new Personne("Noah"),
                new DateEvenement(1, 1, 2000, new HeureDebut(0, 0)),
                new Duree(30)
                );
        var ev2 = new RdvPersonnel(
                new Titre("RDV 2"),
                new Personne("Noah"),
                new DateEvenement(1, 1, 2000, new HeureDebut(0, 0)),
                new Duree(30)
        );

        manager.ajouterEvent(ev1);
        manager.ajouterEvent(ev2);

        var id = ev1.id.getId();

        // Action
        manager.supprimerEvenement(id);

        // Vérification
        assertEquals(1, manager.events.size(), "Il ne devrait rester qu'un seul évènement");
        assertFalse(manager.events.contains(ev1), "Le premier évènement devrait avoir disparu");
    }
}