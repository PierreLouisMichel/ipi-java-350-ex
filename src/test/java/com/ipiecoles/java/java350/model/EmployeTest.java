package com.ipiecoles.java.java350.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EmployeTest 
{
	
	@Test
    public void getNombreAnneeAncienneteNow()
	{
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }
	
	
	//Employé dateEmbauche avec date dans le passé => 2 ans d'anciènneté.
	
	@Test
	public void testGetAnneeEmbaucheNminus2()
	{
		//Given
		Employe employe = new Employe();
		employe.setDateEmbauche(LocalDate.now().minusYears(2));
		
		//When
		Integer nbAnnees = employe.getNombreAnneeAnciennete();
		
		//Then
		Assertions.assertEquals(2, nbAnnees.intValue());
	}
	
	
	//Dans le future => 0
	public void testGetAnneeEmbaucheNplus2()
	{
		//Given
		Employe employe = new Employe();
		employe.setDateEmbauche(LocalDate.now().plusYears(2));
		
		//When
		Integer nbAnnees = employe.getNombreAnneeAnciennete();
		
		//Then
		Assertions.assertEquals(0, nbAnnees.intValue());
	}
	
	//Date aujourd'hui => 0
	//Date d'embauche indéfinie => 0
	public void testGetAnneeEmbaucheNull()
	{
		//Given
		Employe employe = new Employe();
		employe.setDateEmbauche(null);
		
		//When
		Integer nbAnnees = employe.getNombreAnneeAnciennete();
		
		//Then
		Assertions.assertEquals(0, nbAnnees.intValue());
	}
	
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 'T12345', 0, 1.0, 1000.0, 1000, 10",
	            "1, 'T12345', 2, 0.5, 600.0, 1000, 10",
	            "1, 'T12345', 2, 1.0, 1200.0, 1000, 10",
	            "2, 'T12345', 0, 1.0, 2300.0, 1000, 10",
	            "2, 'T12345', 1, 1.0, 2400.0, 1000, 10",
	            "1, 'M12345', 0, 1.0, 1700.0, 1000, 10",
	            "1, 'M12345', 5, 1.0, 2200.0, 1000, 10",
	            "2, 'M12345', 0, 1.0, 1700.0, 1000, 10",
	            "2, 'M12345', 8, 1.0, 2500.0, 1000, 10"
			})
	void testPrimeAnnuelle(Integer performance, String matricule, Integer nbAnneesAnciennete, Double TempsPartiel, Double primeAnnuelle, Double salaire, Double pourcentage) 
	{
	    //Given
		Employe employe = new Employe();
		employe.setMatricule(matricule);
		employe.setPerformance(performance);
		employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneesAnciennete));
		employe.setTempsPartiel(TempsPartiel);
		//When
		Double prime = employe.getPrimeAnnuelle();
		
		//Then
		 Assertions.assertEquals(primeAnnuelle, prime);
	}
	void testAugmenterSalaire(Integer performance, String matricule, Integer nbAnneesAnciennete, Double TempsPartiel, Double primeAnnuelle, Double salaire, Double pourcentage) 
	{
	    //Given
		Employe employe = new Employe();
		employe.setMatricule(matricule);
		employe.setPerformance(performance);
		employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneesAnciennete));
		employe.setTempsPartiel(TempsPartiel);
		employe.setSalaire(salaire);
		
		Double Salaire;
		Salaire = employe.getSalaire();
		Salaire = Salaire+Salaire*pourcentage/100;
		
		//When
		employe.augmenterSalaire(pourcentage);
		
		//Then
		 Assertions.assertEquals(Salaire, employe.getSalaire());
	}
	
	
}
