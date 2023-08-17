package com.tarea;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tarea.lista.FunctionUtils;
import com.tarea.lista.Lista;

record PairDeber(String palabra, Integer conteo) {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairDeber other = (PairDeber) obj;
		return  Objects.equals(palabra, other.palabra);
	}

}

public class MainDeber1 {

	/**
	 * string -> string[ string-> lista (palabra, cout)
	 * 
	 * @param args
	 */

	//esta mal corregir
	public static Lista<PairDeber> contar(String[] vstr) {
		Lista<PairDeber> ret = Lista.NIL;

		for (String s : vstr) {
			PairDeber p = new PairDeber(s, 1);

			var tmp = ret.contains(p);
			if (tmp.isEmpty()) {
				// no lo encontro
				ret = ret.prepend(p);
			} else {
				// encontro
				var item = tmp.get();
				ret.reemplazar(item, new PairDeber(s, item.conteo() + 1));
			}

		}

		return ret;
	}

	
	public static Lista<PairDeber> contar2(String[] vstr) {
		List<PairDeber> ls= Stream.of(vstr)
		.collect(
				Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
		.stream()
		.map(s-> new PairDeber(s.getKey(), s.getValue().intValue())).collect(Collectors.toList());
	
		Lista<PairDeber> ret = Lista.NIL;
		for(PairDeber p: ls) {
			ret=ret.prepend(p);
		}
		return ret;
				
	}
	public static void main(String[] args) {

		String texto = "hola mundo hola hola otro texto mundo";
		Function<String, String[]> fn1 = str -> str.split(" ");

		Function<String[], Lista<PairDeber>> fn2 = MainDeber1::contar2;

		Function<String, Lista<PairDeber>> fog = FunctionUtils.<String, String[], Lista<PairDeber>>comp2().apply(fn1)
				.apply(fn2);
		Lista<PairDeber> cc = fog.apply(texto);

		// System.out.println(cc);

		cc.forEach(s -> {
			System.out.printf("%-10s: %d\n", s.palabra(), s.conteo());
		});

	}
}
