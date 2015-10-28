package model.pk;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import model.Endereco;

public class EnderecoIdGenerator implements IdentifierGenerator {
	public Serializable generate(SessionImplementor session, Object object)
	        throws HibernateException {

	    Connection connection = session.connection();
	    try {
	    	
	    	CallableStatement Statement = 
	                connection.prepareCall( "{ ? = call serializaEndereco( ?) }" );
	    	
	    	Statement.setInt(0, ((Endereco) object).getCodigoPessoa());
	    	Statement.registerOutParameter(0, Types.INTEGER);
	    	
	    	Statement.executeUpdate();
	    	
	    	int codigo = Statement.getInt(0);
	      
	    	return codigo;
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
	    return null;
	}
}