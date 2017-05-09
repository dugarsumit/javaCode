package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sumit.PrimeGenerator;

/**
 * Use this service for interacting with the prime engine.
 * @author sumit
 *
 */
@Service
public class GeneratorService {

	public List<Integer> generatePrimes(int a,int b,int c){
		return PrimeGenerator.primeEngine(a, b, c);
	}
}
