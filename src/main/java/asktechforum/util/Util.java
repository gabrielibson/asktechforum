package asktechforum.util;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Util {
	
	/**
	 * Converte do formato Data SQL 
	 * para String 
	 * 
	 * @param formato formato da saida tipo dia mes anos(dd/MM/yyyy)
	 * @param data data para fazer cach e formata
	 * @return a data formata em string
	 */
	public static String converterDataToString(String formato, Date data){
		String d = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		d = sdf.format(data);
		
		return d;
	}
	
	/**
	 * Converte String para Data SQL 
	 * @param formato formato formato da saida tipo dia mes anos(dd/MM/yyyy)
	 * @param data String com a data a ser formata e convertida
	 * @return retorna a data formatada e convertida para o tipo Date
	 * @throws ParseException
	 */
	public static Date converterStringToDate(String formato, String data) throws ParseException{
		java.sql.Date d = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		
		 d = new java.sql.Date(sdf.parse(data).getTime());
		
		return d;
	}
	/**
	 * Converte Hora de string para formato Time para sql
	 * @param formato
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	public static Time converterStringToTime(String formato, String data) throws ParseException{
		
		java.sql.Time t = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		t = new java.sql.Time(sdf.parse(data).getTime());
		
		return t;
	}
	
	public static String converterTimeToString(String formato, Time time){
		String t = null;
		
		t = time.toString();
		
		return t;
	}
	
	/**
	 * pega a data do sistema
	 * @return
	 */
	public static String getDataSistema(){
		// cria um StringBuilder  
	    StringBuilder sb = new StringBuilder();  
	  
	    // cria um GregorianCalendar que vai conter a hora atual  
	    GregorianCalendar d = new GregorianCalendar();  
	      
	    // anexa do StringBuilder os dados da hora  
	    sb.append( d.get( GregorianCalendar.DAY_OF_MONTH ) );  
	    sb.append( "/" );  
	    sb.append( d.get( GregorianCalendar.MONTH ) );  
	    sb.append( "/" );  
	    sb.append( d.get( GregorianCalendar.YEAR ) );  
	      
	    // retorna a String do StringBuilder  
	    return sb.toString();  
	}
	
	public static String getHoraSistema(){
		// cria um StringBuilder  
	    StringBuilder sb = new StringBuilder();  
	  
	    // cria um GregorianCalendar que vai conter a hora atual  
	    GregorianCalendar d = new GregorianCalendar();  
	      
	    // anexa do StringBuilder os dados da hora  
	    sb.append( d.get( GregorianCalendar.HOUR ) );  
	    sb.append( ":" );  
	    sb.append( d.get( GregorianCalendar.MINUTE ) );  
	    sb.append( ":" );  
	    sb.append( d.get( GregorianCalendar.SECOND ) );  
	      
	    // retorna a String do StringBuilder  
	    return sb.toString();  
	}
	
	/**
	 * Verifica se o valor passado ta no formato
	 * de uma data valida. ex dd/mm/yyyy
	 * @param valor valor para a verificao
	 * @return true se o valor esta no formato de data valida
	 */
	public static boolean ehFormatoData(String valor){
		boolean eformatoData = false;
		
		return eformatoData;
	}
	
	/**
	 * Verifica se o valor passado ta no formato
	 * de uma hora valida. ex hh:mm
	 * @param valor valor para verificao
	 * @return true se o valor esta no formato de hora valida
	 */
	public static boolean ehFormatoHora(String valor){
		boolean eformatoHora = false;
		
		return eformatoHora;
	}
	
	/**
	 * Verificar se contem apenas numero
	 * @param valor valor a ser verificado
	 * @return retorna true se sim
	 */
	public static boolean ehNumero(String valor){
		boolean numero = false;
		
		return numero;
	}
	
	/**
	 * verifica se a string possui apenas letras
	 * @param valor valor a ser verificado
	 * @return retorna true se sim
	 */
	public static boolean ehLetra(String valor){
		boolean letra = false;
		
		return letra;
	}

}