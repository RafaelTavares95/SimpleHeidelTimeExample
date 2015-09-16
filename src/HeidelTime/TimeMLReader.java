package HeidelTime;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TimeMLReader {
	private ArrayList<SingleTimeValue> list;
	
	public TimeMLReader() {
		this.list = new ArrayList<SingleTimeValue>();
	}
	
	public void readString(String xml) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
        Reader in = new StringReader(xml);
        Document doc = builder.build(in);
        Element root = (Element) doc.getRootElement();
        List listtimevalues = root.getChildren();
        Iterator i = listtimevalues.iterator();
        while(i.hasNext()){
        	Element timevalue = (Element) i.next();
        	SingleTimeValue stv = new SingleTimeValue();
        	stv.setData(timevalue.getText());
        	stv.setId(timevalue.getAttributeValue("tid"));
        	stv.setType(timevalue.getAttributeValue("type"));
        	stv.setValue(timevalue.getAttributeValue("value"));
        	list.add(stv);
        }
	}

	public ArrayList<SingleTimeValue> getList() {
		return list;
	}

	public void setList(ArrayList<SingleTimeValue> list) {
		this.list = list;
	}
	
	
}	
