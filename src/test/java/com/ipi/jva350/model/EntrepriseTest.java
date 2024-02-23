package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntrepriseTest {

    @Test
    void testEstDansPlageTrue() {
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.now(), LocalDate.of(2023, 12, 1), LocalDate.of(2024, 4, 10)));
    }

    @Test
    void testEstDansPlageBorneSuperieur() {
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.of(2023, 12, 31), LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)));
    }

    @Test
    public void testEstDansPlageBorneInferieur() {
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 31)));
    }

    //Si je n'avais pas réaliser les tests avant d'écrire ma méthode, je n'aurais pas envisager certains cas limites.
    //Faire les tests m'assurent que ma méthode couvrent les cas

    @ParameterizedTest
    @CsvSource({
            "'2024-12-01',  false",
            "'2024-07-01', false",
            "'2024-07-01', false",
            "'2024-01-01', true",
            "'2024-07-14', true"
    })
    void testEstJourFerie(String d, boolean expected) {
        //
        boolean verif = Entreprise.estJourFerie(LocalDate.parse(d));
        //Then
        assertEquals(expected, verif);
    }


}
