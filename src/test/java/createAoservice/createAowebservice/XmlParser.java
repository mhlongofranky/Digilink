package createAoservice.createAowebservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlParser {
	private String responseXml;
	private SOAPBody soapBody;

	public XmlParser(String responseXml) {
		super();
		this.responseXml = responseXml;
	}

	/**
	 * Converts the response xml string passed to {@link SOAPBody} object
	 */
	private void initialise() {
		if (responseXml != null) {
			MessageFactory factory;
			try {
				factory = MessageFactory.newInstance();
				SOAPMessage message = factory.createMessage(new MimeHeaders(),
						new ByteArrayInputStream(responseXml.getBytes(Charset.forName("UTF-8"))));
				soapBody = message.getSOAPBody();

			} catch (SOAPException soapException) {
				System.out.println("SoapException occurred while parsing response xml" + soapException);
			} catch (IOException ioException) {
				System.out.println("IOException occurred while parsing response xml" + ioException);
			}
		}
	}

	/**
	 * Retrieves Value of the node passed and returns the same
	 *
	 * @param nodeElement
	 * @return Value in the nodeElement
	 */
	public String retrieveNodeValue(String nodeElement) {
		String elementValue;
		if (soapBody == null) {
			initialise();
		}
		if (soapBody.getElementsByTagName(nodeElement) != null
				&& soapBody.getElementsByTagName(nodeElement).getLength() == 0) {
			throw new RuntimeException("Expected Node not available....");
		}
		// soapBody.
		elementValue = soapBody.getElementsByTagName(nodeElement).item(0).getTextContent();
		return elementValue;
	}

	public String retrieveNodeWithChild(String parentNodeElement, String childNodeElement) {
		String nodeValue = "";
		if (soapBody == null) {
			initialise();
		}
		if (soapBody.getElementsByTagName(parentNodeElement) != null
				&& soapBody.getElementsByTagName(parentNodeElement).getLength() == 0) {
			throw new RuntimeException("Expected Node not available....");
		}
		if (soapBody.getElementsByTagName(parentNodeElement).item(0).hasChildNodes()
				&& !childNodeElement.trim().isEmpty() && childNodeElement != null) {
			nodeValue = getChildNodeValue(parentNodeElement, childNodeElement);
		} else {
			nodeValue = soapBody.getElementsByTagName(parentNodeElement).item(0).getTextContent();
		}
		System.out.println("tokenValue:Franko:"+nodeValue);
		return nodeValue;
	}

	public String getChildNodeValue(String parentNodeElement, String childNodeElement) {
		String childNodeValue = "";
		Boolean isChildNodeElementFound = false;
		Element childNode;
		if (soapBody == null) {
			initialise();
		}

		NodeList childNodes = soapBody.getElementsByTagName(parentNodeElement).item(0).getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			childNode = (Element) childNodes.item(i);
			if (childNodeElement.equals(childNode.getNodeName())) {
				childNodeValue = childNode.getTextContent();
				isChildNodeElementFound = true;
			}
		}
		if (!isChildNodeElementFound) {
			throw new RuntimeException("Expected ChildNode not available....");
		}
		return childNodeValue;
	}
}
