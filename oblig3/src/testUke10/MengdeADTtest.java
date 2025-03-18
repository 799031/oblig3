package testUke10;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uke10oppgave.MengdeADT;

public abstract class MengdeADTtest {
	
	abstract MengdeADT<Integer> createInstance();
	private MengdeADT<Integer> even;
	private MengdeADT<Integer> odd;
	private MengdeADT<Integer> empty;
	
	private Integer[] evenNum = {0,2,4};
	private Integer[] oddNum = {1,3,5};


	public MengdeADT<Integer> makeObj(Integer[] arr){
		MengdeADT<Integer> temp = createInstance();
		for(Integer i : arr)
			temp.leggTil(i);
		return temp;
	}
	
	@BeforeEach
	void getEvenElements() {
		even = makeObj(evenNum);
		odd = makeObj(oddNum);
		
		empty = createInstance();
	}
	
	MengdeADT<Integer> makeObj(int element) {
		MengdeADT<Integer> temp = createInstance();
		temp.leggTil(element);
		return temp;
	}
	
	MengdeADT<Integer> singleEntry(Integer num){
		Integer[] tempNum = {num};
		return makeObj(tempNum);
	}
	
	@Test
	void erTom() {
		assertTrue(empty.erTom());
		assertFalse(even.erTom());		
	}
	
	@Test
	void inneholder() {
		assertFalse(empty.inneholder(0));
		assertFalse(even.inneholder(1));	
		assertTrue(even.inneholder(2));		
	}
	
	@Test
	void erDelmengdeAv() {
		assertFalse(even.erDelmengdeAv(odd));
		assertTrue(empty.erDelmengdeAv(even));
		assertTrue(empty.erDelmengdeAv(empty));
		assertTrue(singleEntry(2).erDelmengdeAv(even));
	}
	
	@Test
	void erLik() {
		assertTrue(empty.erLik(empty));
		assertFalse(empty.erLik(even));
		assertFalse(odd.erLik(even));
		assertTrue(even.erLik(even));
	}
	
	@Test
	void erDisjunkt() {
		assertTrue(empty.erDisjunkt(empty));
		assertFalse(even.erDisjunkt(even));
		assertTrue(even.erDisjunkt(odd));
		odd.leggTil(2);
		assertFalse(even.erDisjunkt(odd));
	}
	
	@Test
	void snitt() {
		MengdeADT<Integer> empty2 = (MengdeADT<Integer>) empty.snitt(even);
		assertTrue(empty2.erLik(empty));
		empty2 = (MengdeADT<Integer>) odd.snitt(even);
		assertTrue(empty2.erLik(empty));

		empty2.leggTil(2);
		MengdeADT<Integer> har2 = (MengdeADT<Integer>) empty2.snitt(even);
		assertTrue(har2.erLik(singleEntry(2)));
	}
	
	@Test
	void union() {
		Integer[] comb = {0,1,2,3,4,5};
		MengdeADT<Integer> combAns = makeObj(comb);
		MengdeADT<Integer> combTest = (MengdeADT<Integer>) even.union(odd);
		assertTrue(combAns.antallElementer() == 6);

		assertTrue(combAns.erLik(combTest));
		assertTrue(even.erLik(even.union(empty)));
	}
	
	@Test
	void minus() {
		assertTrue(even.erLik(even.minus(odd)));
		odd.leggTil(2);
		Integer[] ans = {0,4};
		assertTrue(makeObj(ans).erLik(even.minus(odd)));
	}

	@Test
	void leggTil() {
		assertTrue(empty.erTom());
		empty.leggTil(2);
		assertFalse(empty.erTom());
		assertTrue(empty.antallElementer() == 1);
		assertTrue(empty.inneholder(2));
		empty.leggTil(2);
		assertTrue(empty.antallElementer() == 1);
	}

	@Test
	void leggTilAlleFra() {
		empty.leggTilAlleFra(even);
		assertTrue(empty.erLik(even));
		
		empty.leggTilAlleFra(odd);
	
		Integer[] comb = {0,1,2,3,4,5};
		MengdeADT<Integer> combAns = makeObj(comb);

		empty.erLik(combAns);	
	}
	
	@Test
	void fjern() {
		int fjernet = even.fjern(2);
		assertTrue(fjernet == 2);
		assertTrue(even.antallElementer() == 2);
		assertFalse(even.inneholder(fjernet));
		assertTrue(even.fjern(1) == null);
	}
		
	@Test
	void antallElementer() {
		assertTrue(empty.antallElementer() == 0);
		assertTrue(even.antallElementer() == 3);
	}
}

















