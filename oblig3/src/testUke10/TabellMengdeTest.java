package testUke10;

import uke10oppgave.TabellMengde;

class TabellMengdeTest extends MengdeADTtest{

	@Override
	protected TabellMengde<Integer> createInstance() {
		return new TabellMengde<Integer>();
	}

}
