package uke10oppgave;

public class LinearNode<T> {
	public T data;
	public LinearNode<T> neste;
	private int antallNoder;
	
	public LinearNode() {
		data = null;
		neste = null;
		antallNoder = 0;
    }
	
	public LinearNode(T data) {
        this.data = data;
        this.neste = null;
        antallNoder = 1;
    }
	
	public int getAntalNoder() {
		return antallNoder;
	}
	
	public void addData(T data) {
		LinearNode<T> nyNode = new LinearNode<>(data);
		
		if(this.data == null) {
			this.data = nyNode.data;
			antallNoder++;
			return;
		}
		if(this.neste != null)
			nyNode.neste = this.neste;
		
		this.neste = nyNode;
		antallNoder++;
	}
	
	public boolean fjernNeste() {
		if(this.neste == null)
			return false;
		this.neste = this.neste.neste;
		antallNoder--;
		return true;
	}
	
	T fjernIndex(int index){
		if(index < 0 || antallNoder <= index)
			return null;
		LinearNode<T> temp = this;
		for(int i=0; i<index-1; i++)
			temp = this.neste;
		T data = temp.data;
		temp.neste = temp.neste.neste;
		antallNoder--;
		return data;
	}
	
	public T[] tilTabell() {
		@SuppressWarnings("unchecked")
		T[] tabell = (T[]) new Object[antallNoder];
		
		LinearNode<T> temp = this;
		for(int i=0; i<antallNoder; i++) {
			tabell[i] = temp.data;
			temp = temp.neste;
		}
		return tabell;
	}
}










