package model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusQuartoConverter implements AttributeConverter<StatusQuarto, String>{
	
	@Override
	public String convertToDatabaseColumn(StatusQuarto statusQuarto) {
		switch (statusQuarto) {
			case Ativo:
				return "A";
			case Inativo:
				return "I";
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + statusQuarto);				
		}
	}
	
	@Override
	public StatusQuarto convertToEntityAttribute(String dbData) {
		switch (dbData) {
			case "A":
				return StatusQuarto.Ativo;
			case "I":
				return StatusQuarto.Inativo;
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + dbData);				
		}
	}
}