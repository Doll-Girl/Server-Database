package net.asgardsolutions.dollgirl.serverdb.utilities;

import java.io.FileOutputStream;

import java.io.IOException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Functions {


	public static boolean StoreObjectToXml(String filename, Object obj) {
		try {
			FileOutputStream file = new FileOutputStream(filename, true);
			JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(obj, file);
			jaxbMarshaller.marshal(obj, System.out);

		} catch (JAXBException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	

}
