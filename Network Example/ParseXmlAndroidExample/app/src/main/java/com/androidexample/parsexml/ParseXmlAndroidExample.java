package com.androidexample.parsexml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ParseXmlAndroidExample extends Activity {

	List<XmlValuesModel> myData = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_xml_android_example);
		
		final TextView output 		= (TextView) findViewById(R.id.output);
		final Button bparsexml      = (Button) findViewById(R.id.bparsexml);
		
		//Static XML data 
		final String XMLData = "<?xml version=\"1.0\"?><login><status>OK</status><jobs><job><id>4</id><companyid>4</companyid><company>Android Example</company><address>Parse XML Android</address><city>Tokio</city><state>Xml Parsing Tutorial</state><zipcode>601301</zipcode><country>Japan</country><telephone>9287893558</telephone><fax>1234567890</fax><date>2012-03-15 12:00:00</date></job><job><id>5</id><companyid>6</companyid><company>Xml Parsing In Java</company><address>B-22</address><city>Cantabill</city><state>XML Parsing Basics</state><zipcode>201301</zipcode><country>America</country><telephone>9287893558</telephone><fax>1234567890</fax><date>2012-05-18 13:00:00</date></job></jobs></login>";
		
		String dataToBeParsed = "Click on button to parse XML.\n\n XML DATA : \n\n"+XMLData;
		output.setText(dataToBeParsed);
		
		bparsexml.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
		
				try{
					
					BufferedReader br=new BufferedReader(new StringReader(XMLData));
					InputSource is=new InputSource(br);
					
					//Create XML Parser
					XMLParser parser=new XMLParser();
					
					SAXParserFactory factory = SAXParserFactory.newInstance();
			   		SAXParser sp = factory.newSAXParser();
			   		XMLReader reader=sp.getXMLReader();
			   		reader.setContentHandler(parser);
			   		reader.parse(is);
			   		
			   		myData=parser.list;
			   		
			   		if(myData!=null){
			   			
			   			String OutputData = "";

			    	    for (XmlValuesModel xmlRowData : myData) {
						  if(xmlRowData!=null)
						  {  
							  
							  
							int     id 			= xmlRowData.getid();
					    	int     companyid 	= xmlRowData.getcompanyid();
				    	    String  company 	= xmlRowData.getcompany();
					        String  address		= xmlRowData.getaddress();
							String  city		= xmlRowData.getcity();
							String  state		= xmlRowData.getstate();
							String  zipcode		= xmlRowData.getzipcode();
							String  country		= xmlRowData.getcountry();
							String  telephone	= xmlRowData.gettelephone();
					    	String  date 		= xmlRowData.getdate();
					        
					    	
					        OutputData += "Job Node : \n\n "+ company +" | "
					                                       + address +" | " 
					                                       + city +" | " 
					                                       + state +" | " 
					                                       + zipcode +" | "
					                                       + country +" | "
					                                       + telephone +" | "
					                                       + date +" \n\n "
					                                       
					                               ;

						  }
						  else
							  Log.e("Jobs", "Jobs value null");
					    }
			    	    
			    	    output.setText(OutputData);
			   		}   
				}
			   	catch(Exception e){Log.e("Jobs", "Exception parse xml :"+e);}
			} 	
		});	   	
	}
}
