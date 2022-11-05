package net.mips.compiler;

import java.io.IOException;

public class Parser {

	private Scanner scanner;
	
	
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public Parser(String nomFile) throws IOException,ErreurCompilation{
		this.scanner = new Scanner(nomFile);
	}
	
	public void testAccept(Tokens T,CodesErr C) throws IOException,ErreurCompilation{
		if(scanner.getSymbCour().getToken()==T) {
			scanner.symbSuiv();
			
		}
		else {
			throw new ErreurSyntaxique(C);
		}
		
	}
	
	public void consts() throws IOException,ErreurCompilation{
		testAccept(Tokens.CONST_TOKEN, CodesErr.CONST_ERR);
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
		testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		while(scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
			testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
			testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
			testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);	
		}
	}
	
	
	public void vars() throws IOException,ErreurCompilation{
		testAccept(Tokens.VAR_TOKEN, CodesErr.VAR_ERR);
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);	
		}
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
	}
	
	public void Fact() throws IOException,ErreurCompilation {
		if (scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.NUM_TOKEN) {
			testAccept(Tokens.NUM_TOKEN,CodesErr.NUM_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.PARG_TOKEN) {	
			testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
			Expr();
			testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
		}
	}
	
	public void Mulop() throws IOException,ErreurCompilation{
		if (scanner.getSymbCour().getToken()==Tokens.MUL_TOKEN) {
			testAccept(Tokens.MUL_TOKEN,CodesErr.MUL_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.DIV_TOKEN) {
			testAccept(Tokens.DIV_TOKEN,CodesErr.DIV_ERR);
		}

	}
	
	public void Term() throws IOException,ErreurCompilation{
		Fact();
		while (scanner.getSymbCour().getToken()==Tokens.MUL_TOKEN || scanner.getSymbCour().getToken()==Tokens.DIV_TOKEN ) {
			Mulop();
			Fact();
		}
	}
	
	public void Addop()  throws IOException,ErreurCompilation{
		if (scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN) {
			testAccept(Tokens.PLUS_TOKEN,CodesErr.PLUS_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN) {
			testAccept(Tokens.MOINS_TOKEN,CodesErr.MOINS_ERR);
		}
	}
	
	public void Expr() throws IOException,ErreurCompilation {
		Term();
		while (scanner.getSymbCour().getToken()==Tokens.PLUS_TOKEN || scanner.getSymbCour().getToken()==Tokens.MOINS_TOKEN ) {
			Addop();
			Term();
		}
		
	}
	
	
	public void Affec()throws IOException,ErreurCompilation {
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testAccept(Tokens.AFFEC_TOKEN,CodesErr.AFFEC_ERR);
		Expr();
	}
	
	public void Relop() throws IOException,ErreurCompilation {
		if(scanner.getSymbCour().getToken()==Tokens.EG_TOKEN) {
			testAccept(Tokens.EG_TOKEN,CodesErr.EG_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.DIFF_TOKEN) {
			testAccept(Tokens.DIFF_TOKEN,CodesErr.DIFF_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.SUP_TOKEN) {
			testAccept(Tokens.SUP_TOKEN,CodesErr.SUP_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.INF_TOKEN) {
			testAccept(Tokens.INF_TOKEN,CodesErr.INF_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.SUPEG_TOKEN) {
			testAccept(Tokens.SUPEG_TOKEN,CodesErr.SUPEG_ERR);
		}
		else if (scanner.getSymbCour().getToken()==Tokens.INFEG_TOKEN) {
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
		while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			Expr();
		}
		testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
	}
	
	public void Lire()throws IOException,ErreurCompilation {
		testAccept(Tokens.READ_TOKEN, CodesErr.READ_ERR);
		testAccept(Tokens.PARG_TOKEN,CodesErr.PARG_ERR);
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		while(scanner.getSymbCour().getToken()==Tokens.VIR_TOKEN) {
			testAccept(Tokens.VIR_TOKEN,CodesErr.VIR_ERR);
			testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		}
		testAccept(Tokens.PARD_TOKEN,CodesErr.PARD_ERR);
	}
	
	public void inst() throws IOException,ErreurCompilation{
		if (scanner.getSymbCour().getToken()==Tokens.ID_TOKEN) {
			Affec();
		}
		else if (scanner.getSymbCour().getToken()==Tokens.IF_TOKEN) {
			Si();
		}
		else if (scanner.getSymbCour().getToken()==Tokens.WHILE_TOKEN) {
			Tantque();
		}
		else if (scanner.getSymbCour().getToken()==Tokens.WRITE_TOKEN) {
			Ecrire();
		}
		else if (scanner.getSymbCour().getToken()==Tokens.READ_TOKEN) {
			Lire();
		}
		else if(scanner.getSymbCour().getToken()==Tokens.BEGIN_TOKEN) {
			insts();
		}	
	}
	
	public void insts() throws IOException,ErreurCompilation{

		testAccept(Tokens.BEGIN_TOKEN, CodesErr.BEGIN_ERR);	
		inst();
		while (scanner.getSymbCour().getToken()==Tokens.PVIR_TOKEN) {
			testAccept(Tokens.PVIR_TOKEN, CodesErr.PVIR_ERR);	
			inst();
		}
		testAccept(Tokens.END_TOKEN, CodesErr.END_ERR);
		
	}
	
	
	
	public void Block() throws IOException,ErreurCompilation{
		
		if(scanner.getSymbCour().getToken()==Tokens.CONST_TOKEN) {
			consts();
		}
		if(scanner.getSymbCour().getToken()==Tokens.VAR_TOKEN) {
			vars();
		}
		
		insts();
	}
	
	
	public void program()  throws IOException,ErreurCompilation{
		testAccept(Tokens.PROGRAM_TOKEN,CodesErr.PROGRAM_ERR);
		testAccept(Tokens.ID_TOKEN,CodesErr.ID_ERR);
		testAccept(Tokens.PVIR_TOKEN,CodesErr.PVIR_ERR);
		Block();
		testAccept(Tokens.PNT_TOKEN,CodesErr.PNT_ERR);
	}
	
	
	
	
	public static void main(String[] args) throws IOException,ErreurCompilation{
		Parser parser=new Parser("C:\\Users\\PC\\Desktop\\emsi 4iir 1\\Compilation\\TP\\coo.txt");
		parser.getScanner().initMotsCles();
		parser.getScanner().lireCar();
		
		parser.getScanner().symbSuiv();
		parser.program();	
		
		if(parser.scanner.getSymbCour().getToken()==Tokens.EOF_TOKEN) {
			System.out.println("l’analyse syntaxique a réussi");
		}
		else {
			throw new ErreurSyntaxique(CodesErr.EOF_ERR);
		}
	}

}
