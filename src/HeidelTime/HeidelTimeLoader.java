package HeidelTime;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jeeves.utils.Xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import de.unihd.dbs.heideltime.standalone.DocumentType;
import de.unihd.dbs.heideltime.standalone.HeidelTimeStandalone;
import de.unihd.dbs.heideltime.standalone.OutputType;
import de.unihd.dbs.heideltime.standalone.components.impl.TimeMLResultFormatter;
import de.unihd.dbs.heideltime.standalone.exceptions.DocumentCreationTimeMissingException;
import de.unihd.dbs.uima.annotator.heideltime.HeidelTime;
import de.unihd.dbs.uima.annotator.heideltime.resources.Language;

public class HeidelTimeLoader {


    private static HeidelTimeStandalone hts = null;

    private static HeidelTimeLoader instance;


    private HeidelTimeLoader(){
         hts = new HeidelTimeStandalone(Language.ENGLISH, DocumentType.NARRATIVES, OutputType.TIMEML);
                
    }

    public synchronized static HeidelTimeLoader getInstance(){
         if(instance==null){
             instance = new HeidelTimeLoader();
         }
         return instance;
    }

    public static void main(String[] args) throws DocumentCreationTimeMissingException, JDOMException, IOException{
    	  hts = new HeidelTimeStandalone(Language.ENGLISH, DocumentType.NARRATIVES, OutputType.TIMEML);
    	
          String reStri = hts.process("Today at 1:32pm", new Date(), new TimeMLResultFormatter());
          System.out.println(reStri);
          TimeMLReader reader = new TimeMLReader();
          reader.readString(reStri);
          ArrayList<SingleTimeValue> values = reader.getList();
          System.out.println("Foram encontradas "+ values.size() +" expressões de tempo:");
          for (SingleTimeValue x : values) {
        	  System.out.println(x.getData() + "=" + x.getValue());
          }
    }


}
