package com.ipi.jva350.model;

import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.LinkedHashSet;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileTest {

    @Autowired
    SalarieAideADomicileRepository salarieAideADomicileRepository;
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Test
    void testALegalementDroitADesCongesPayesDefaultValue() {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile();
        // When :
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertFalse(res);
    }

    @Test
    void testALegalementDroitADesCongesPayesNominal() {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28), LocalDate.now(), 20, 2.5,
                120, 15, 8);
        // When :
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertTrue(res);
    }

    @Test
    void testALegalementDroitADesCongesPayesTrue() {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28), LocalDate.now(), 20, 2.5,
                10, 1, 8);
        // When :
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertTrue(res, "avec 10 jours travaillés en N-1 (au moins), le résultat doit être vrai");
    }

    @Test
    void testALegalementDroitADesCongesPayesFalse() {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28), LocalDate.now(), 20, 2.5,
                9, 1, 8);
        // When :
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertFalse(res, "avec 9 jours travaillés en N-1 (au plus), le résultat doit être faux");
    }

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17', '2023-12-28', 9",
            "'2023-12-17', '2023-12-29', 11",
            "'2023-12-17', '2023-12-30', 11",
            "'2023-12-17', '2023-12-31', 11",
            "'2023-12-17', '2024-01-08', 17"
    })
    void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedNb) {
        // Given :
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28), LocalDate.now(), 20, 2.5,
                9, 1, 8);
        // When :
        LinkedHashSet<LocalDate> resNb = monSalarie.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFin));
        // Then :
        Assertions.assertEquals(expectedNb, resNb.size());
    }

    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Alexis");
        salarie.setMoisEnCours(LocalDate.parse("2024-02-23"));
        salarie.setCongesPayesAcquisAnneeNMoins1(25);
        salarie.setMoisDebutContrat(LocalDate.parse("2023-01-01"));
        salarieAideADomicileRepository.save(salarie);
        //When
        long limiteEntrepriseCongesPermis = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(salarie.getMoisEnCours(),
                salarie.getCongesPayesAcquisAnneeNMoins1(),
                salarie.getMoisDebutContrat(), LocalDate.parse("2023-01-20"), LocalDate.parse("2023-02-01"));

        // Then
        Assertions.assertEquals(19L, limiteEntrepriseCongesPermis);
    }

}
