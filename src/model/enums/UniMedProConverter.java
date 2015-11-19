package model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UniMedProConverter implements AttributeConverter<UniMedProduto, String>{
	
	@Override
	public String convertToDatabaseColumn(UniMedProduto uniMedProduto) {
		switch (uniMedProduto) {
			case KG:
				return "KG";
			case LT:
				return "LT";
			case PC:
				return "PC";
			case MT:
				return "MT";
			case UN:
				return "UN";
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + uniMedProduto);				
		}
	}
	
	@Override
	public UniMedProduto convertToEntityAttribute(String dbData) {
		switch (dbData) {
			case "KG":
				return UniMedProduto.KG;
			case "LT":
				return UniMedProduto.LT;
			case "PC":
				return UniMedProduto.PC;
			case "MT":
				return UniMedProduto.MT;
			case "UN":
				return UniMedProduto.UN;
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + dbData);				
		}
	}
}

