package model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusServicoConverter implements AttributeConverter<StatusServico, String>{
	@Override
	public String convertToDatabaseColumn(StatusServico statusServico) {
		switch (statusServico) {
			case Aberto:
				return "A";
			case Fechado:
				return "I";
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + statusServico);				
		}
	}
	
	@Override
	public StatusServico convertToEntityAttribute(String dbData) {
		switch (dbData) {
			case "A":
				return StatusServico.Aberto;
			case "F":
				return StatusServico.Fechado;
			default:
				throw new IllegalArgumentException("Valor desconhecido: " + dbData);				
		}
	}
}