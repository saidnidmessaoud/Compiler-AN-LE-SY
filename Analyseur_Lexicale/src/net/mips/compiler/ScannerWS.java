package net.mips.compiler;
import java.io.IOException;
import java.util.ArrayList;
public class ScannerWS extends Scanner {
		
	private ArrayList<Symboles> tableSymb;
	private int PLACE_SYMB;
	
	private static int offset=0;
	
	public ScannerWS(String fichier) throws IOException, ErreurCompilation{
		super(fichier);
		this.tableSymb=new ArrayList<Symboles>();
		this.PLACE_SYMB=0;
	}

	
	public int getPLACE_SYMB() {
		return PLACE_SYMB;
	}
	
	public ArrayList<Symboles> getTableSymb() {
		return tableSymb;
	}

	public void initMotsCles() {
		tableSymb.add(new Symboles(Tokens.PROGRAM_TOKEN, "program"));
		tableSymb.add(new Symboles(Tokens.CONST_TOKEN, "const"));
		tableSymb.add(new Symboles(Tokens.VAR_TOKEN, "var"));
		tableSymb.add(new Symboles(Tokens.BEGIN_TOKEN, "begin"));
		tableSymb.add(new Symboles(Tokens.END_TOKEN, "end"));
		tableSymb.add(new Symboles(Tokens.IF_TOKEN, "if"));
		tableSymb.add(new Symboles(Tokens.THEN_TOKEN, "then"));
		tableSymb.add(new Symboles(Tokens.WHILE_TOKEN, "while"));
		tableSymb.add(new Symboles(Tokens.DO_TOKEN, "do"));
		tableSymb.add(new Symboles(Tokens.WRITE_TOKEN, "write"));
		tableSymb.add(new Symboles(Tokens.READ_TOKEN, "read"));
	}
	
	
	
	public void codageLex() {
		String nom1= getSymbCour().getNom();
		for(Symboles symb:tableSymb) {
			String nom2=symb.getNom();
			if(nom1.equalsIgnoreCase(nom2)) {
				getSymbCour().setToken(symb.getToken());
				return;
			}
		}
		getSymbCour().setToken(Tokens.ID_TOKEN);
	}
	
	
	public void entreSymb(ClasseIdf c) {
		if (c== ClasseIdf.CONSTANTE || c==ClasseIdf.VARIABLE || c==ClasseIdf.PROGRAMME) {
			Symboles h=new Symboles();
			
			h.setToken(getSymbCour().getToken());
			h.setNom(getSymbCour().getNom());
			
			h.setClasse(c);
			
			offset++;
			tableSymb.add(h);
		}
		
	}
	
	public void chercherSymb() {
		String nom1= getSymbCour().getNom();
		for(int j=0;j<tableSymb.size();j++) {
			String nom2=tableSymb.get(j).getNom();
			
			if(nom1.equalsIgnoreCase(nom2)) {
				PLACE_SYMB=j;
				return;
			}
		}
		PLACE_SYMB=-1;
	}





	public static void main(String args[]) throws IOException, ErreurCompilation {
		
		ScannerWS scannerws=new ScannerWS("C:\\Users\\PC\\Desktop\\emsi 4iir 1\\Compilation\\TP\\coo.txt");
		scannerws.initMotsCles();
		scannerws.lireCar();
		while(scannerws.getCarCour()!=EOF) {
			scannerws.symbSuiv();
			System.out.println(scannerws.getSymbCour().getToken());
		}
	}
	
	
	
	
	
	
	
}
