package com.tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.tarea.lista.Lista;

public class MainEjercicio {

	static <U, T> List<U> map(List<T> ls, Function<T, U> fn) {

		List<U> ret = new ArrayList<>();
		for (T t : ls) {
			ret.add(fn.apply(t));
		}
		return ret;
	}

	static <T> T reduce(List<T> ls, Function<T, Function<T, T>> fn) {
		T acum = ls.get(0);
		for (int i = 1; i < ls.size(); i++) {
			T elem = ls.get(i);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}

	static Integer fold1(List<Integer> ls, Integer identidad, Function<Integer, Function<Integer, Integer>> fn) {

		/**
		 * (1,2,3,4) fold= (((0+1)+2)+3)+4
		 */
		int index = 1;
		Integer acum = identidad;
		for (Integer elem : ls) {
			// System.out.printf("it %d: %d + %d\n",index++,acum,elem);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}

	// concaternar numeros
	static String fold2(List<Integer> ls, String identidad, Function<String, Function<Integer, String>> fn) {

		/**
		 * (1,2,3,4) fold= (((0+1)+2)+3)+4
		 */
		int index = 1;
		String acum = identidad;
		for (Integer elem : ls) {
			// System.out.printf("it %d: %d + %d\n",index++,acum,elem);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}
	//f: UXT->U
	static <T, U> U foldLeft(List<T> ls, U identidad, Function<U, Function<T, U>> fn) {

		U acum = identidad;
		for (T elem : ls) {

			acum = fn.apply(acum).apply(elem);//f(id,t)
		}
		return acum;
	}
	
	//24/07/2023
	
	//f: TXU->U
	static <T, U> U foldRight(List<T> ls,U identity, Function<T, Function<U, U>> fn){
		U acum = identity;
		for(int i= ls.size()-1;i>=0;i--) {
			//f(t,id)
			acum=fn.apply(ls.get(i)).apply(acum);
		}
		return acum;
	}
	

	public static void testReduction(String[] args) {

		List<Integer> ls = List.of(1, 2, 3, 4);
		var ret = map(ls, x -> x * 1.2);

		System.out.println(ret);

		Integer suma = reduce(ls, x -> y -> x + y);

		System.out.println("Suma=" + suma);

		var lista = Lista.of(1, 2, 3, 4, 5);
		var ret2 = lista.map(x -> x * 1.2);
		System.out.println(ret2);

		var ret3 = lista.mapIt(x -> x * 1.2);
		System.out.println(ret3);
	}

	public static void testLeftFolding(String[] args) {


		List<Integer> ls = List.of(1, 2, 3, 4);
		// todo es de izquierda a derecha
		Integer suma = fold1(ls, 0, x -> y -> x + y);
		System.out.println("La suma es: " + suma);

		// en el lenguaje la suma es una concatenacion
		String concat = fold2(ls, "", str -> x -> str + x);
		// String concat =fold2(ls, "", str->x->str.concat(x.toString()));
		System.out.println("La concat es: " + concat);

		// foldlefts

		Integer suma2 = foldLeft(ls, 0, x -> y -> x + y);
		System.out.println("La suma2 es: " + suma2);

		String f = foldLeft(ls, "", str -> x -> str + x);

		System.out.println("La concat2   es: " + f);
		// orden de las operaciones
		var ret = foldLeft(ls, "0", str -> x -> String.format("(%s + %d)", str, x));

		System.out.println(ret);

		// {1,2,3,4) salida {"1","2","3","4")

		Function<List<String>, Function<Integer, List<String>>> fn = list -> t -> {
			String tmp = t.toString();
			list.add(tmp);
			return list;
		};

		List<String> ret5 = foldLeft(ls, new ArrayList<>(), fn);
		List<String> ret6 = foldLeft(ls, new ArrayList<>(), list -> t -> {

			list.add("s" + t.toString());
			return list;
		});

		System.out.println(ret6);

		List<Integer> values = List.of(1, 2, 3, 5, 8, 2, 5, 1, 2, 15, 1, 32, 1);
		Map<Integer, Integer> counts = new HashMap<>();
		for (Integer t : values) {
			if (counts.containsKey(t)) {
				var cc = counts.get(t);
				counts.put(t, cc + 1);
			} else {
				counts.put(t, 1);
			}
		}
		System.out.println(counts);
		// UXT ->U
		Function<Map<Integer, Integer>, Function<Integer, Map<Integer, Integer>>> fn2 = map -> t -> {
			if (map.containsKey(t)) {
				var cc = counts.get(t);
				map.put(t, cc + 1);
			} else {
				map.put(t, 1);
			}
			return map;
		};
		Map<Integer, Integer> valores = foldLeft(values, new HashMap<>(), map -> t -> {
			if (map.containsKey(t)) {
				var cc = counts.get(t);
				map.put(t, cc + 1);
			} else {
				map.put(t, 1);
			}
			return map;
		});
		System.out.println(valores);

		Map<Integer, Integer> vi = new HashMap<>();

		var counts2 = foldLeft(values, vi, fn2);
		System.out.println(counts2);
	}

	public static void testRight(String[] args) {

		List<Integer> ls = List.of(1, 2, 3, 4);
		var suma1=foldRight(ls, 0, x->y->x+y);
		System.out.println("Suma: "+suma1);
		
		
		//"(1+(2+(3+(4+(5+0)))"
				Function<Integer, Function<String, String>> fun= x->str->String.format("(%d+%s)", x,str);
				var s= foldRight(ls, "0",fun);
				System.out.println(s);
	}
	public static void main(String[] args) {
		Lista<Integer> ls =Lista.of(1,2,3,4);
		var suma= ls.reduce(0, x->y->x+y);
		System.out.println(suma);
		
		var sts= ls.foldLeft("0",str->x->String.format("(%s+%d)", str, x));
		
		System.err.println(sts);
		
		//--
		var str2= ls.foldRight("0", x->str->String.format("(%d+%s)", x, str));
		System.out.println(str2);
		
		//-- invertir la lista 
		//==> {4,3,2,1)
		
		Function<Lista<Integer>, Function<Integer,Lista<Integer> >> fn = list -> t -> {
		
			var lista=list.prepend(t);
			return lista;
		};
		
		var invertida2 = ls.foldLeft(Lista.NIL, fn);
		var invertida = ls.foldLeft(Lista.NIL, list -> t ->list.prepend(t));
		System.out.println(invertida);
		System.out.println("inv: "+invertida2);
		
		var invertidaR = ls.foldRight(Lista.NIL, t -> list ->list.append(t));
		System.out.println(invertidaR);
		
		//size 
		Integer size =ls.foldLeft( 0, u->lista->u+1);
		var size2= ls.foldRight(0, t->u->u+1);
		
		System.out.println(size);
		System.out.println(size2);
		//System.out.println(System.clearProperty("java.version"));
		
		
	
		
		var map = ls.foldLeft(Lista.NIL, list->t->list.append(t.toString()+"a"));
	/**/
		System.out.println(map);
	}
}





