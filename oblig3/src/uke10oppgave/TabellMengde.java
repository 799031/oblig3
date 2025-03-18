package uke10oppgave;


public class TabellMengde<T> implements MengdeADT<T> {
	
	private T[] tabell;
	
	@SuppressWarnings("unchecked")
	public TabellMengde(){
		tabell = (T[]) new Object[0];
	}
	
	@Override
	public boolean erTom() {
		return (this.antallElementer() == 0);
	}

	@Override
	public boolean inneholder(T element) {
		for(int i = 0; i<tabell.length; i++)
			if(tabell[i] != null && tabell[i].equals(element))
				return true;
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for(int i = 0; i<tabell.length; i++)
			if(!annenMengde.inneholder(tabell[i]))
				return false;
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if(annenMengde.antallElementer() != this.antallElementer())
			return false;
		
		for(T index : tabell)
			if(!annenMengde.inneholder(index))
				return false;
		return true;
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for(T index : tabell)
			if(annenMengde.inneholder(index))
				return false;
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> nyMengde = new TabellMengde<T>();
		for(T index : tabell)
			if(annenMengde.inneholder(index))
				nyMengde.leggTil(index);
		return nyMengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> nyMengde = new TabellMengde<T>();
		nyMengde.leggTilAlleFra(this);
		nyMengde.leggTilAlleFra(annenMengde);
		return nyMengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> nyMengde = new TabellMengde<T>();
		for(T index : tabell)
			if(!annenMengde.inneholder(index))
				nyMengde.leggTil(index);
		return nyMengde;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void leggTil(T element) {
		if(this.inneholder(element))
			return;
		T[] tempTabell = this.tilTabell();
		
		tabell = (T[]) new Object[this.antallElementer()+1];	
		for(int i = 0; i<tempTabell.length; i++) 
			tabell[i] = tempTabell[i];
		tabell[tempTabell.length] = element;
	}
	
	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] tempTabell = annenMengde.tilTabell();
		for(T index : tempTabell)
			this.leggTil(index);
		
	}

	private int finnIndex(T element) {
		for(int i = 0; i<this.antallElementer(); i++) {
			if(tabell[i].equals(element))
				return i;
		}
		return -1;
	}
	
	@Override
	public T[] tilTabell() {
		@SuppressWarnings("unchecked")
		T[] tempTabell = (T[]) new Object[tabell.length];
		int count = 0;
		for(T temp : tabell)
			tempTabell[count++] = temp;
		return tempTabell;
	}
	
	@Override
	public T fjern(T element) {
		int index = finnIndex(element);
		if(index == -1)
			return null;
		
		T temp = tabell[index];
		@SuppressWarnings("unchecked")
		T[] tempTab = (T[]) new Object[this.antallElementer()-1];
		int count = 0;
		for(int i = 0; i<this.antallElementer(); i++)
			if(i != index)
				tempTab[count++] = tabell[i];
		
		tabell = tempTab;
		return temp;
	}

	@Override
	public int antallElementer() {
		return tabell.length;
	}

}




