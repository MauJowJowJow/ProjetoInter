package model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusQuartoConverter implements AttributeConverter<StatusQuarto, String>{
	
	@Override
	public String convertToDatabaseColumn(StatusQuarto statusQuarto) {
		switch (statusQuarto) {
			case Disponivel:
				return "D";
			case Reservado:
				return "R";				
			case Inativo:
				return "I";
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + statusQuarto);				
		}
	}
	
	@Override
	public StatusQuarto convertToEntityAttribute(String dbData) {
		switch (dbData) {
			case "D":
				return StatusQuarto.Disponivel;
			case "R":
				return StatusQuarto.Reservado;				
			case "I":
				return StatusQuarto.Inativo;
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + dbData);				
		}
	}
}