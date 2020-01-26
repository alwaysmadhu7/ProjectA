package com.me.test;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class ParseUtil {

	static int aliasCount;
	static int orgCount;
	static int idnCount;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("org.xml"));
		document.getDocumentElement().normalize();
		Element root = document.getDocumentElement();
		// System.out.println("============"+document.getChildNodes().item(0));
		NodeList nList = document.getElementsByTagName("OrganizationMarket");
		System.out.println("============================");
		visitChildNodes(nList);

		NodeList nList2 = document.getElementsByTagName("OrganizationIdentifier");
		visitChildNodes(nList2);
		System.out.println(aliasCount + "========" + idnCount);

		NodeList nList3 = document.getElementsByTagName("OrganizationAlias");
		visitChildNodes(nList3);
		System.out.println(aliasCount + "========" + idnCount);

		NodeList nList4 = document.getElementsByTagName("OrganizationRoot");
		System.out.println("============================");
		visitChildNodes(nList4);

	}

	// This function is called recursively

	private static void visitChildNodes(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
				if (node.getNodeName().equalsIgnoreCase("OrganizationAlias")) {
					aliasCount += 1;
				}
				if ("OrganizationRoot".equalsIgnoreCase(node.getNodeName())) {
					orgCount += 1;
				}
				if ("OrganizationIdentifier".equalsIgnoreCase(node.getNodeName())) {
					idnCount += 1;
				}
				// Check all attributes
				if (node.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = node.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node tempNode = nodeMap.item(i);
						// .out.println("Attr name: " + i + tempNode.getNodeName() + "; Value = " +
						// tempNode.getNodeValue());
					}
					if (node.hasChildNodes()) {
						// We got more childs; Let's visit them as well
						visitChildNodes(node.getChildNodes());
					}
				}
			}
		}
	}
}
