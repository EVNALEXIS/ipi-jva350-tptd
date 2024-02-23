package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EntrepriseTest {

    @Test
    public void testEstDansPlageTrue(){
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.now(), LocalDate.of(2023,12,1), LocalDate.of(2024, 4,10)));
    }

    @Test
    public void testEstDansPlageBorneSuperieur(){
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.of(2023,12,31), LocalDate.of(2023,12,1), LocalDate.of(2023,12,31)));
    }

    @Test
    public void testEstDansPlageBorneInferieur(){
        Assertions.assertTrue(Entreprise.estDansPlage(LocalDate.of(2024,12,1), LocalDate.of(2024,12,1), LocalDate.of(2024,12,31)));
    }
}
