package asktechforum.factory;

/**
 * Classe que implementa Factory Method e Singleton
 * para criar fabricas menores que efetivamente
 * criam os objetos.
 * 
 * Para criar as fabricas,deve-se informar via parametro.
 * 
 * @author diego.ferreira
 *
 */
public class FactoryDataBase implements FabricaDeFabricas{
	private static FactoryDataBase instancia;
	
	private FactoryDataBase() {
	}
	
	public static synchronized FactoryDataBase getInstancia(){
		if(instancia == null){
			instancia = new FactoryDataBase();
		}
		
		return instancia;
	}
	
	/**
	 *  Metodo que cria a fabrica para uma familia.
	 * @param tipoFabrica - tipo de fabrica a ser criada.
	 * @return
	 */
	public FabricaDAO criarFabrica(String tipoFabrica){
		FabricaDAO f = null;
		
		if(tipoFabrica.equalsIgnoreCase("JDBC")){
			f = new FabricaJDBC();
		}
		//os outros tipos de fabrica,seriam adicionados aqui.
		//exemplo
		/*
		 * else if(tipoFabrica.equalsIgnoreCase("txt"){
		 * 	f = new FabricaTxt();
		 * }
		 */
		
		return f;
	}

}
