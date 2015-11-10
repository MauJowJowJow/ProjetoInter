package model;

import java.util.Date;
import javax.persistence.*;
import model.enums.StatusServico;
import model.pk.ServicoPK;

@Entity
@Table(name="servico")

public class Servico extends ModelDefault {

	@EmbeddedId
	private ServicoPK pk = new ServicoPK();
	
	@Column(name="datSer")
	private Date dataServico;
	
	@Column(name="staSer")
	private StatusServico statusServico;
}
