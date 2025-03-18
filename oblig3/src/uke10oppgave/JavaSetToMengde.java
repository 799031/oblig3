package uke10oppgave;

import java.util.HashSet;
import java.util.Set;




public class JavaSetToMengde<T> implements MengdeADT<T> {
    
	
	private Set<T> mengde;

	
    public JavaSetToMengde() {
        this.mengde = new HashSet<>();
    }

    @Override
    public boolean erTom() {
        return mengde.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return mengde.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (T element : mengde) {
            if (!annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (T element : mengde) {
            if (annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        for (T element : mengde) {
            if (annenMengde.inneholder(element)) {
                resultat.leggTil(element);
            }
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        resultat.leggTilAlleFra(this);
        resultat.leggTilAlleFra(annenMengde);
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        for (T element : mengde) {
            if (!annenMengde.inneholder(element)) {
                resultat.leggTil(element);
            }
        }
        return resultat;
    }

    @Override
    public void leggTil(T element) {
        mengde.add(element);
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T element : annenMengde.tilTabell()) {
            this.leggTil(element);
        }
    }

    @Override
    public T fjern(T element) {
        if (mengde.remove(element)) {
            return element;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T[] tilTabell() {
        return (T[]) mengde.toArray();
    }

    @Override
    public int antallElementer() {
        return mengde.size();
    }
}