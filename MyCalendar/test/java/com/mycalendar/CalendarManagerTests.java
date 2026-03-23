package com.mycalendar;

import com.mycalendar.types.Periodique;
import com.mycalendar.types.Reunion;
import com.mycalendar.types.RdvPersonnel;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarManagerTests {

    /**
     * Date de référence pour les tests
     */
    private static final LocalDateTime INITIAL_DATE =
            LocalDateTime.of(2026, 1, 1, 15, 0);

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

        PERIODIC_EVENT_ONE = new Periodique(
                "Evènement 1",
                "Noah",
                INITIAL_DATE,
                60,
                1
        );

        NON_PERIODIC_EVENT_ONE = new Reunion(
                "Evènement 1",
                "Loup",
                INITIAL_DATE.plusDays(1),
                90,
                "Paris",
                "Mr X, Loup, Elias"
        );

        NON_PERIODIC_EVENT_TWO = new RdvPersonnel(
                "Evènement 2",
                "Mr X",
                INITIAL_DATE.plusDays(4),
                90
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

        CALENDAR_MANAGER_WITH_ONE_EVENT.ajouterEvent(
                new Reunion(
                        "Réunion test",
                        "Noah",
                        INITIAL_DATE,
                        60,
                        "Nancy",
                        "Noah, Loup, Elias"
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Reunion(
                        "Réunion test",
                        "Noah",
                        INITIAL_DATE,
                        60,
                        "Nancy",
                        "Noah, Loup, Elias"
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new RdvPersonnel(
                        "Réunion test 2",
                        "Loup",
                        INITIAL_DATE.plusDays(1).minusHours(5),
                        60
                )
        );

        CALENDAR_MANAGER_WITH_MANY_EVENT.ajouterEvent(
                new Periodique(
                        "Réunion test 3",
                        "Mr X",
                        INITIAL_DATE.plusDays(4).plusHours(6),
                        150,
                        8
                )
        );

        CALENDAR_MANAGER_WITH_PERIODIC_EVENT.ajouterEvent(
                new Periodique(
                        "Réunion test Périodique",
                        "Mr X",
                        INITIAL_DATE,
                        60,
                        2
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

        calendarManager.ajouterEvent(
                new Reunion(
                        "Réunion test",
                        "Noah",
                        INITIAL_DATE,
                        60,
                        "Nancy",
                        "Noah, Loup, Elias"
                )
        );
        var listEvents = calendarManager.events;

        assertEquals(1, listEvents.size(), "La liste doit contenir 1 seul évènement");

    }

    @Test
    @DisplayName("Récupération d'aucun évènement dans une période donnée")
    public void testGetZeroEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_MANY_EVENT;

        var dateDebut = INITIAL_DATE.minusWeeks(2);
        var dateFin = dateDebut.minusWeeks(1);
        var res = calendarManager.eventsDansPeriode(dateDebut, dateFin);

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");

    }

    @Test
    @DisplayName("Récupération d'un évènement non périodique dans une période donnée")
    public void testGetOneNonPeriodicEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_MANY_EVENT;

        var dateDebut = INITIAL_DATE.minusMinutes(5);
        var dateFin = dateDebut.plusMinutes(10);
        var res = calendarManager.eventsDansPeriode(dateDebut, dateFin);

        assertEquals(1, res.size(), "La liste doit contenir 1 seul évènement");
        assertEquals("REUNION", res.getFirst().type, "Le type de l'évènement doit être 'REUNION'");

    }

    @Test
    @DisplayName("Récupération d'un évènement périodique dans une période donnée")
    public void testGetOnePeriodicEventInPeriod() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;

        var dateDebut = INITIAL_DATE.minusMinutes(5);
        var dateFin = dateDebut.plusMinutes(10);
        var res = calendarManager.eventsDansPeriode(dateDebut, dateFin);

        assertEquals(1, res.size(), "La liste doit contenir 1 seul évènement");
        assertEquals("PERIODIQUE", res.getFirst().type, "Le type de l'évènement doit être 'PERIODIQUE'");

    }

    @Test
    @DisplayName("Récupération évènement périodique avec en entrée de la méthode la même date de début et de fin")
    public void testGetPeriodicEventWithSameDateInEntry() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;

        var res = calendarManager.eventsDansPeriode(INITIAL_DATE, INITIAL_DATE);

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");

    }

    @Test
    @DisplayName("Récupération d'un évènement périodique où la date de début se trouve avant la date de début passée " +
            "en paramètre de la méthode")
    public void testGetPeriodicEventWithDateBeforeStartingDate() {

        var calendarManager = CALENDAR_MANAGER_WITH_PERIODIC_EVENT;

        var dateDebut = INITIAL_DATE.plusMinutes(5);
        var dateFin = INITIAL_DATE.plusMinutes(10);
        var res = calendarManager.eventsDansPeriode(dateDebut, dateFin);

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");

    }

    @Test
    @DisplayName("Récupération d'un évènement non périodique où la date de début se trouve avant la date de début " +
            "passée en paramètre de la méthode")
    public void testGetNonPeriodicEventWithDateBeforeStartingDate() {

        var calendarManager = CALENDAR_MANAGER_WITH_ONE_EVENT;

        var dateDebut = INITIAL_DATE.plusMinutes(5);
        var dateFin = INITIAL_DATE.plusMinutes(10);
        var res = calendarManager.eventsDansPeriode(dateDebut, dateFin);

        assertEquals(0, res.size(), "La liste ne doit contenir aucun évènement");

    }

    @Test
    @DisplayName("Test de conflit avec un évènement périodique et un évènement non périodique")
    public void testConflictWithOnePeriodicEventAndOneNonPeriodicEvent() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var res = calendarManager.conflit(PERIODIC_EVENT_ONE, NON_PERIODIC_EVENT_ONE);

        Assertions.assertFalse(res, "La méthode doit renvoyer false");

    }

    @Test
    @DisplayName("Test de conflit avec deux évènements non périodique")
    public void testConflictWithTwoNonPeriodicEvent() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var res = calendarManager.conflit(NON_PERIODIC_EVENT_ONE, NON_PERIODIC_EVENT_TWO);

        Assertions.assertFalse(res, "La méthode doit renvoyer false");

    }

    @Test
    @DisplayName("Test de conflit avec un évènements non périodique et un évènement périodique")
    public void testConflictWithOneNonPeriodicEventAndOnePeriodicEvent() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var res = calendarManager.conflit(NON_PERIODIC_EVENT_ONE, PERIODIC_EVENT_ONE);

        Assertions.assertFalse(res, "La méthode doit renvoyer false");

    }

    @Test
    @DisplayName("Test de conflit où la date de fin du premier évènement se trouve après la date de début du second")
    public void testConflictWithFirstEndDateAfterSecondStartDate() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var event1 = new Reunion(
                "Evènement 1",
                "Loup",
                INITIAL_DATE,
                90,
                "Paris",
                "Mr X, Loup, Elias"
        );

        var event2 = new Reunion(
                "Evènement 1",
                "Loup",
                INITIAL_DATE.minusMinutes(30),
                60,
                "Paris",
                "Mr X, Loup, Elias"
        );

        var res = calendarManager.conflit(event1, event2);

        Assertions.assertTrue(res, "La méthode doit renvoyer true");

    }

    @Test
    @DisplayName("Test de conflit où la date de début du premier évènement se trouve après la fin du second")
    public void testConflictWithFirstStartDateAfterSecondEndDate() {

        var calendarManager = EMPTY_CALENDAR_MANAGER;

        var event1 = new Reunion(
                "Evènement 1",
                "Loup",
                INITIAL_DATE.plusDays(1),
                90,
                "Paris",
                "Mr X, Loup, Elias"
        );

        var event2 = new Reunion(
                "Evènement 1",
                "Loup",
                INITIAL_DATE,
                60,
                "Paris",
                "Mr X, Loup, Elias"
        );

        var res = calendarManager.conflit(event1, event2);

        Assertions.assertFalse(res, "La méthode doit renvoyer false");

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
        Réunion : Réunion test à Nancy avec Noah / Loup / Elias
        RDV : Réunion test 2 à 2026-01-02T10:00
        Événement périodique : Réunion test 3 tous les 8 jours
        """;

        var actual = outputStreamCaptor.toString()
                .replace("\r\n", "\n")
                .strip();

        assertEquals(expected.strip(), actual);

    }

}
