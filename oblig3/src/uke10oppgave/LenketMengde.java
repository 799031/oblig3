package uke10oppgave;




public class LenketMengde<T> implements MengdeADT<T> {

	
	private LinearNode<T> data;
	
	public LenketMengde() {
		this.data = new LinearNode<T>();
	}

	@Override
	public boolean erTom() {
		return (data.getAntalNoder() == 0);
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> temp = data;
		while(temp != null) {
			if(temp.data != null && temp.data.equals(element))
				return true;
			temp = temp.neste;
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		LinearNode<T> temp = data;
		while(temp != null) {
			if(temp.data != null && !annenMengde.inneholder(temp.data))
				return false;
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if(annenMengde.antallElementer() != this.antallElementer())
			return false;
		
		return this.erDelmengdeAv(annenMengde);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		LinearNode<T> temp = data;
		while(temp != null) {
			if(annenMengde.inneholder(temp.data))
				return false;
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> nyMengde = new TabellMengde<T>();
		
		LinearNode<T> temp = data;
		while(temp != null) {
			if(annenMengde.inneholder(temp.data))
				nyMengde.leggTil(temp.data);
			temp = temp.neste;
		}
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
		
		LinearNode<T> temp = data;
		while(temp != null) {
			if(temp.data != null && !annenMengde.inneholder(temp.data))
				nyMengde.leggTil(temp.data);
			temp = temp.neste;
		}
		return nyMengde;
	}

	@Override
	public void leggTil(T element) {
		if(this.inneholder(element))
			return;
		data.addData(element);
	}
	
	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] tempTabell = annenMengde.tilTabell();
		for(T index : tempTabell)
			this.leggTil(index);
	}
	
	@Override
	public T fjern(T element) {
		int index = 0;
		LinearNode<T> temp = data;
		while(temp != null) {
			if(temp.data != null && temp.data.equals(element)) {
				data.fjernIndex(index);
				return element;
			}
			temp = temp.neste;
			index++;
		}
		return null;
	}
	
	public void printTab() {
		String str = "";
		LinearNode<T> temp = data;
		for(int i = 0; i<this.antallElementer(); i++) {
			str += temp.data.toString() + "\n";
			temp = temp.neste;
		}
		System.out.println(str);
	}

	public T[] tilTabell() {
		return data.tilTabell();
		
	}
	
	@Override
	public int antallElementer() {
		return data.getAntalNoder();
	}

}





