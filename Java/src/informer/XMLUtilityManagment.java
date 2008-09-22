/*
 * XMLManagment.java
 *
 * Created on 31 2005, 01:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


package informer;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLUtilityManagment {

    public static Document readXMLFile( String filename) throws IOException{

        DocumentBuilder builder;
             DocumentBuilderFactory factory =
             DocumentBuilderFactory.newInstance();
             try {
                    builder= factory.newDocumentBuilder();
                    Document document = builder.parse(filename);
                    return document;
             }catch(Exception e){
            	 throw new IOException();
             }
    }

    public static void writeXMLFile(Node node,String filename) throws IOException{

        try{
                // Prepare the DOM document for writing
                 DOMSource source = new DOMSource(node);
                 // Prepare the output file

                StreamResult result = new StreamResult(filename);

                // Write the DOM document to the file
                Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(source, result);

          } catch (TransformerConfigurationException e) {
              throw new IOException();
          } catch (TransformerException e) {
        	  throw new IOException();
          }
    }
}
