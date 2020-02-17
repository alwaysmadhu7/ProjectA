package com.me.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XpathEg {
	static Set<Object> hashSet = new HashSet();
	static ArrayList<Object> arrayList;

	public static void main(String[] args) throws Exception {
		hashSet = new XpathEg().printAll("xmlnodes.properties");
		arrayList = new ArrayList<>(hashSet);
		System.out.println(arrayList);

		DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = Factory.newDocumentBuilder();
		Document doc = builder.parse("eg.xml");

		// Normalize the XML Structure; It's just too important !!
		doc.getDocumentElement().normalize();

		// Here comes the root node
		Element root = doc.getDocumentElement();
		System.out.println(root.getNodeName());
		System.out.println(root.getChildNodes().getLength());
		Node ab = root.getChildNodes().item(1);
		System.out.println(ab.getNodeName());

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();

		XPathExpression expr = xpath.compile("//" + ab.getNodeName() + "/*");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		System.out.println(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			Element el = (Element) nodes.item(i);
			// System.out.println("tag: " + el.getNodeName());
			myMethod(el.getNodeName());

			if (null != el.getFirstChild() && el.getFirstChild().getNodeType() == Node.TEXT_NODE) {
				// System.out.println("inner value:" + el.getFirstChild().getNodeValue());
				myMethod(el.getFirstChild().getNodeName());
				/*
				 * if (arrayList.get(0).equals("" + el.getFirstChild().getNodeName())) {
				 * System.out.println("First:" + incr++); } if (arrayList.get(1).equals("" +
				 * el.getFirstChild().getNodeName())) { System.out.println("Two:" + incr2++); }
				 * if (arrayList.get(2).equals("" + el.getFirstChild().getNodeName())) {
				 * System.out.println("Third:" + incr3++); } if (arrayList.get(3).equals("" +
				 * el.getFirstChild().getNodeName())) { System.out.println("Forth:" + incr4++);
				 * }
				 */
			}

			NodeList children = el.getChildNodes();
			for (int k = 0; k < children.getLength(); k++) {
				Node child = children.item(k);
				if (child.getNodeType() != Node.TEXT_NODE) {
					// System.out.println("child tag: " + child.getNodeName());
					myMethod(child.getNodeName());
					if (child.getFirstChild().getNodeType() == Node.TEXT_NODE) {
						// System.out.println("inner child value:" +
						// child.getFirstChild().getNodeValue());

					}
				}
			}
		}
	}

	static void myMethod(String string) {
		for (int j = 0; j < arrayList.size(); j++) {
			if (arrayList.get(j).equals("" + string)) {
				System.out.println("Count: " + j);
			}
		}
	}

	Set<Object> printAll(String filename) {

		Properties prop = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return null;
			}

			prop.load(input);
			Set<Object> objects = prop.keySet();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.keySet();
	}
}