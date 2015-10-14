/* Código adaptado do projeto Stella Caelum
 * https://github.com/caelum/caelum-stella
 * */

package model.bean.formatters;

import java.util.regex.Pattern;

import model.enums.TipoPessoa;

/**
 * @author Leonardo Bessa
 *
 */

public class CGCFormatter {
    private final BaseFormatter base;
    public static final Pattern CNPJFORMATED = Pattern.compile("(\\d{2})[.](\\d{3})[.](\\d{3})/(\\d{4})-(\\d{2})");
	public static final Pattern CNPJUNFORMATED = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
	
	public static final Pattern CPFFORMATED = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})");
	public static final Pattern CPFUNFORMATED = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");

    public CGCFormatter(TipoPessoa tipoPessoa) {
    	switch(tipoPessoa){
    		case Fisica:
    			this.base = new BaseFormatter(CPFFORMATED, "$1.$2.$3-$4", CPFUNFORMATED, "$1$2$3$4");
    			break;
    		case Juridica:
    			this.base = new BaseFormatter(CNPJFORMATED, "$1.$2.$3/$4-$5", CNPJUNFORMATED, "$1$2$3$4$5");
    			break;
    	default:
    		base=null;
    	}
    }

	public String format(String value) {
        return base.format(value);
    }

	public String unformat(String value) {
        return base.unformat(value);
    }

	public boolean isFormatted(String value) {
		return base.isFormatted(value);
	}

	public boolean canBeFormatted(String value) {
		return base.canBeFormatted(value);
	}
}
