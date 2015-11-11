package asktechforum.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UsuarioUtil {
	
	public UsuarioUtil() {
	}
	
	/** 
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,  
     * retorna null - para facilitar em casos onde formulários podem ter campos 
     * de datas vazios. 
     * @param dataString String no formato dd/MM/yyyy a ser formatada 
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula 
     * @throws Exception Caso a String esteja no formato errado 
     */  
    public static Date converterStringData(String dataString) {   
        Date data = null;
    	
        try {  
        	
	        if(dataString.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$")) {
	            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	            data = new Date(formatter.parse(dataString).getTime());
            }
	        
        } catch (ParseException e) {              
            data = null;
        }  
        return data;  
    }
    
}