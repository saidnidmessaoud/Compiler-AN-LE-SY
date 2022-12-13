package net.mips.compiler;

public enum CodesErr {
	CAR_INC_ERR("Symbole inconnu"),
	FIC_VID_ERR("Erreur d'ouverture de fichier"),
	
	
	
	
	
	
	PROGRAM_ERR("Mot clé program attendu !"),
	ID_ERR("Identificateur attendu !"),
	PVIR_ERR("Symbole ; attendu !"),
	VIR_ERR("Symbole , attendu !"),
	NUM_ERR("Numero  attendu !"),
	CONST_ERR("Symbole const attendu !"),
	VAR_ERR("Symbole var attendu !"),
	BEGIN_ERR("Symbole begin attendu !"),
	END_ERR("Symbole end attendu !"),
	IF_ERR("Symbole if attendu !"),
	THEN_ERR("Symbole then attendu !"),
	WHILE_ERR("Symbole while attendu !"),
	DO_ERR("Symbole do attendu !"),
	WRITE_ERR("Symbole write attendu !"),
	READ_ERR("Symbole read attendu !"),
	EG_ERR("Symbole egal attendu !"),
	DIFF_ERR("Symbole different attendu !"),
	SUP_ERR("Symbole superieur attendu !"),
	INF_ERR("Symbole inferieur attendu !"),
	SUPEG_ERR("Symbole sup ou egal attendu !"),
	INFEG_ERR("Symbole inf ou egal attendu !"),
	AFFEC_ERR("Symbole affectation attendu !"),
	PLUS_ERR("Symbole plus attendu !"),
	MOINS_ERR("Symbole moins attendu !"),
	MUL_ERR("Symbole * attendu !"),
	DIV_ERR("Symbole / attendu !"),
	PARG_ERR("Symbole ( attendu !"),
	PARD_ERR("Symbole ) attendu !"),
	PNT_ERR("Symbole . attendu !"),
	EOF_ERR("Symbole EOF attendu !"),
	
	ID_NON_DECLARE_ERR("identificateur non déclaré"),
	DOUBLE_DECLARATION_ERR("double déclaration"),
	change_valeur_const("constant ne peut change de valeur"),
	Nom_PROGRAM_ERR("nom de programme non autorisé");
	
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private CodesErr(String message) {
		this.message=message;
	}
}



