package net.mips.compiler;

import java.io.IOException;

public class ParserWS extends Parser{
	
	private ScannerWS scannerws;
	
	public ParserWS(String fichier)  throws IOException,ErreurCompilation{
		super(fichier);
		this.scannerws=new ScannerWS(fichier);
	}
	
	public ScannerWS getScannerws() {
		return scannerws;
	}
	
	public void testAccept(Tokens T,CodesErr C) throws IOException,ErreurCompilation{		
		if(scannerws.getSymbCour().getToken()==T) {
			System.out.println(scannerws.getSymbCour().getToken());
			scannerws.symbSuiv();
			
		}
		else {
			throw new ErreurSyntaxique(C);
		}
		
	}
	
	public void Test_Insere(Tokens T,ClasseIdf E,CodesErr C)  throws IOException,ErreurCompilation{
		
		if (this.scannerws.getSymbCour().getToken()==T) {
			System.out.println(scannerws.getSymbCour().getToken());
			scannerws.entreSymb(E);
			scannerws.symbSuiv();
			
		}
		else {
			throw new ErreurSyntaxique(C);
		}
	}
	
	public void Test_Cherche(Tokens T,CodesErr E) throws IOException,ErreurCompilation{
		
		if (this.scannerws.getSymbCour().getToken()==T) {
			
			scannerws.chercherSymb();
			
			
		}
		else {
			throw new ErreurSyntaxique(E); 
		}
	}
	
	public void program()  throws IOException,ErreurCompilation{
		testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
		Test_Insere(Tokens.ID_TOKEN,ClasseIdf.PROGRAMME,CodesErr.ID_ERR);
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		Block();
		testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
	}
	
	
	
	
	public void consts() throws IOException,ErreurCompilation{
		testAccept(Tokens.CONST_TOKEN, CodesErr.CONST_ERR);
		
		Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		
		
		if(scannerws.getPLACE_SYMB()!=-1) {
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
				throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
			}
		}
		
		

		Test_Insere(Tokens.ID_TOKEN,ClasseIdf.CONSTANTE,CodesErr.ID_ERR);
		testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
		testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		
		while(scannerws.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
					throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
				}
			}
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				throw new ErreurSemantique(CodesErr.DOUBLE_DECLARATION_ERR);
			}
			
			Test_Insere(Tokens.ID_TOKEN,ClasseIdf.CONSTANTE,CodesErr.ID_ERR);
			testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
			testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
			testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);	
		}
	}
	
	
	public void vars() throws IOException,ErreurCompilation{
		testAccept(Tokens.VAR_TOKEN, CodesErr.VAR_ERR);
		
		Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		
		
		if(scannerws.getPLACE_SYMB()!=-1) {
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
				throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
			}
		}
		
		
		if(scannerws.getPLACE_SYMB()!=-1) {
			throw new ErreurSemantique(CodesErr.DOUBLE_DECLARATION_ERR);
		}
		Test_Insere(Tokens.ID_TOKEN,ClasseIdf.VARIABLE,CodesErr.ID_ERR);
		while(scannerws.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			
			Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
					throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
				}
			}
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				throw new ErreurSemantique(CodesErr.DOUBLE_DECLARATION_ERR);
			}
			Test_Insere(Tokens.ID_TOKEN,ClasseIdf.VARIABLE,CodesErr.ID_ERR);
		}
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
	}
	
	
	
	public void Affec()throws IOException,ErreurCompilation {
		Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		
		
		if(scannerws.getPLACE_SYMB()!=-1) {
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
				throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
			}
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.CONSTANTE) {
				throw new ErreurSemantique(CodesErr.change_valeur_const);
			}
		}
		
		
		if(scannerws.getPLACE_SYMB()==-1) {
			throw new ErreurSemantique(CodesErr.ID_NON_DECLARE_ERR);
		}
		

		
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
		Expr();
	}
	
	
	
	
	
	public void Lire()throws IOException,ErreurCompilation {
		testAccept(Tokens.READ_TOKEN, CodesErr.READ_ERR);
		testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
		Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		
		
		if(scannerws.getPLACE_SYMB()!=-1) {
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
				throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
			}
			if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.CONSTANTE) {
				throw new ErreurSemantique(CodesErr.change_valeur_const);
			}
		}
		
		
		if(scannerws.getPLACE_SYMB()==-1) {
			throw new ErreurSemantique(CodesErr.ID_NON_DECLARE_ERR);
		}
		

		
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		while(scannerws.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
					throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
				}
				if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.CONSTANTE) {
					throw new ErreurSemantique(CodesErr.change_valeur_const);
				}
			}
			
			
			if(scannerws.getPLACE_SYMB()==-1) {
				throw new ErreurSemantique(CodesErr.ID_NON_DECLARE_ERR);
			}
			

			
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		}
		testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
	}
	
	public void Fact() throws IOException,ErreurCompilation {
		if (scannerws.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			Test_Cherche(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			
			
			if(scannerws.getPLACE_SYMB()!=-1) {
				if(scannerws.getTableSymb().get(scannerws.getPLACE_SYMB()).getClasse()==ClasseIdf.PROGRAMME) {
					throw new ErreurSemantique(CodesErr.Nom_PROGRAM_ERR);
				}
			}
			
			if(scannerws.getPLACE_SYMB()==-1) {
				throw new ErreurSemantique(CodesErr.ID_NON_DECLARE_ERR);
			}
			
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.NUM_TOKEN) {
			testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.PARG_TOKEN) {	
			testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
			Expr();
			testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
		}
	}
	
	public void Mulop() throws IOException,ErreurCompilation{
		if (scannerws.getSymbCour().getToken()==Tokens.MUL_TOKEN) {
			testAccept(Tokens.MUL_TOKEN,CodesErr.MUL_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.DIV_TOKEN) {
			testAccept(Tokens.DIV_TOKEN,CodesErr.DIV_ERR);
		}

	}
	
	public void Term() throws IOException,ErreurCompilation{
		this.Fact();
		while (scannerws.getSymbCour().getToken()==Tokens.MUL_TOKEN || scannerws.getSymbCour().getToken()==Tokens.DIV_TOKEN ) {
			Mulop();
			this.Fact();
		}
	}
	
	public void Addop()  throws IOException,ErreurCompilation{
		if (scannerws.getSymbCour().getToken()==Tokens.PLUS_TOKEN) {
			testAccept(Tokens.PLUS_TOKEN,CodesErr.PLUS_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
			testAccept(Tokens.MOINS_TOKEN,CodesErr.MOINS_ERR);
		}
	}
	
	public void Expr() throws IOException,ErreurCompilation {
		Term();
		while (scannerws.getSymbCour().getToken()==Tokens.PLUS_TOKEN || scannerws.getSymbCour().getToken()==Tokens.MOINS_TOKEN ) {
			Addop();
			Term();
		}
		
	}
	
	public void Relop() throws IOException,ErreurCompilation {
		if(scannerws.getSymbCour().getToken()==Tokens.EG_TOKEN) {
			testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.DIFF_TOKEN) {
			testAccept(Tokens.DIFF_TOKEN,CodesErr.DIFF_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.SUP_TOKEN) {
			testAccept(Tokens.SUP_TOKEN,CodesErr.SUP_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.INF_TOKEN) {
			testAccept(Tokens.INF_TOKEN,CodesErr.INF_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.SUPEG_TOKEN) {
			testAccept(Tokens.SUPEG_TOKEN,CodesErr.SUPEG_ERR);
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.INFEG_TOKEN) {
			testAccept(Tokens.INFEG_TOKEN,CodesErr.INFEG_ERR);
		}
	}
	
	public void Cond() throws IOException,ErreurCompilation{
		Expr();
		Relop();
		Expr();	
	}
	
	public void Si() throws IOException,ErreurCompilation{
		testAccept(Tokens.IF_TOKEN, CodesErr.IF_ERR);
		Cond();
		testAccept(Tokens.THEN_TOKEN, CodesErr.THEN_ERR);
		inst();
	}
	
	public void Tantque() throws IOException,ErreurCompilation {
		testAccept(Tokens.WHILE_TOKEN, CodesErr.WHILE_ERR);		
		Cond();
		testAccept(Tokens.DO_TOKEN, CodesErr.DO_ERR);
		inst();
	}
	
	public void Ecrire() throws IOException,ErreurCompilation{
		testAccept(Tokens.WRITE_TOKEN, CodesErr.WRITE_ERR);	
		testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
		Expr();
		while(scannerws.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			Expr();
		}
		testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
	}
	
	public void inst() throws IOException,ErreurCompilation{
		if (scannerws.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			Affec();
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.IF_TOKEN) {
			Si();
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.WHILE_TOKEN) {
			Tantque();
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.WRITE_TOKEN) {
			Ecrire();
		}
		else if (scannerws.getSymbCour().getToken()==Tokens.READ_TOKEN) {
			this.Lire();
		}
		else if(scannerws.getSymbCour().getToken()==Tokens.BEGIN_TOKEN) {
			insts();
		}	
	}
	
	public void insts() throws IOException,ErreurCompilation{

		testAccept(Tokens.BEGIN_TOKEN, CodesErr.BEGIN_ERR);	
		inst();
		while (scannerws.getSymbCour().getToken()==Tokens.PVIR_TOKEN) {
			testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);	
			inst();
		}
		testAccept(Tokens.END_TOKEN, CodesErr.END_ERR);
		
	}
	
	
	
	public void Block() throws IOException,ErreurCompilation{
		
		if(scannerws.getSymbCour().getToken()==Tokens.CONST_TOKEN) {
			this.consts();
		}
		if(scannerws.getSymbCour().getToken()==Tokens.VAR_TOKEN) {
			this.vars();
		}
		
		insts();
	}
	
	

	
	public static void main(String[] args) throws IOException,ErreurCompilation{
		ParserWS parserws=new ParserWS("C:\\Users\\PC\\Desktop\\emsi 4iir 1\\Compilation\\TP\\coo.txt");
		parserws.scannerws.initMotsCles();
		parserws.scannerws.lireCar();
		
		parserws.scannerws.symbSuiv();
		parserws.program();	
		
		if(parserws.scannerws.getSymbCour().getToken()==Tokens.EOF_TOKEN) {
			System.out.println("l’analyse semantique a réussi");
		}
		else {
			throw new ErreurSyntaxique(CodesErr.EOF_ERR);
		}
	}
	
	
}
