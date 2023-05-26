package com.beheos.universidad.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilerias {
	
	public static String getFormatoFecha(Date fecha){
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaFormateada = formato.format(fecha);
		return fechaFormateada;
	}
	
	public static String getGenerarMatricula(){
		String matricula = "";
		for(int i= 0; i < 5; i++){
			matricula += Math.floor((Math.random() * 100) + 1);
		}
		return matricula;
	}

}
