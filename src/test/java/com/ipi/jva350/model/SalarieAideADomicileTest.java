package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class SalarieAideADomicileTest {
    @Test
    public void testaLegalementDroitADesCongesPayesTrue(){
        //Given
        SalarieAideADomicile sad = new SalarieAideADomicile("",LocalDate.of(2023,6,12), LocalDate.now(),120,15,10,20,10);
        //When
        boolean res= sad.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertTrue(res);
    }
    @Test
    public void testaLegalementDroitADesCongesPayesFalse(){
        //Given
        SalarieAideADomicile sad = new SalarieAideADomicile("",LocalDate.of(2023,6,12), LocalDate.now(),120,15,9,20,10);
        //When
        boolean res= sad.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertFalse(res);
    }


}
