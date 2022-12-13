package net.mips.compiler;

public class ErreurSemantique extends ErreurCompilation {
	
	public ErreurSemantique(CodesErr code) {
		super(code.getMessage());
	}

}
