package TreeTagger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

 
/**
 * 
 * Classe que faz a análise morfossintática simples de uma frase usando o treetagger.
 * @author Rafael
 *
 */
public class TreeTaggerLoader {
	
	/**
	 * Método que converte uma string, contendo o texto que vai ser analisado, em uma lista de tokens. 
	 * @param texto
	 * @return
	 */
	public static ArrayList<String> asList(String texto){
		ArrayList<String> test = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(texto);
		while(st.hasMoreTokens()){
			test.add(st.nextToken());
		}
		
		return test;
	}
	
	public static void main(String[] args) throws IOException, TreeTaggerException {	
		// Point TT4J to the TreeTagger installation directory. The executable is expected
	    // in the "bin" subdirectory - in this example at "/opt/treetagger/bin/tree-tagger"
		
		Scanner in = new Scanner(System.in);
		System.out.println("Digite com o seguinte padrão: sigla do idioma(pt, en, ge, fr) : frase");
		String text = in.nextLine();
		String array[] = new String[2]; 
		array =  text.split(" : ");
		String model = null;
		
		switch (array[0]) {
		case "pt":
			model = "C:/TreeTagger/lib/pt.par";
			break;
		case "en":
			model = "C:/TreeTagger/lib/english-utf8.par";
			break;
		case "ge":
			model = "C:/TreeTagger/lib/german-utf8.par";
			break;
		case "fr":
			model = "C:/TreeTagger/lib/french.par";
			break;
		default:
			break;
		}
		
	    System.setProperty("treetagger.home", "C:/TreeTagger"); 
	    TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
	    try {
	      tt.setModel(model);
	      tt.setHandler(new TokenHandler<String>() {
	        public void token(String token, String pos, String lemma) {
	          System.out.println(token + "\t" + pos + "\t" + lemma);
	        }
	      });
	     tt.process(asList(array[1]));
	    }
	    finally {
	      tt.destroy();
	      in.close();
	    }
		
	}
}
