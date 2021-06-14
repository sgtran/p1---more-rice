package com.example.demo.controller;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class scoreModifier {

    private static final String FILENAME = ".\\scores.xml";

    public static void main(String[] args) {
        System.out.println(topScores()[0]);
    }

    public static int[] topScores() {
        int[] scores = new int[10];
        for (int i = 0; i < 10; i++) {
            scores[i] = getScore(topPlayers()[i]);
        }
        return scores;
    }

    public static String[] topPlayers() {
        String[] top = new String[10];

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            NodeList list = doc.getElementsByTagName("elements");

            Node names = list.item(0);

            if (names.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) names;

                for (int i = 0; i < 10; i++) {
                    String str = "name" + topIndexes()[i];
                    String name = element.getElementsByTagName(str).item(0).getTextContent();
                    top[i] = name;
                }

                return top;

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static boolean containing(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static int[] topIndexes() {
        int[] topArray = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};

        int[] scores = scoresToArray();


        for (int i = 0; i < 10; i++) {
            int max = 0;
            int maxIndex = 0;

            for (int j = 0; j < scores.length; j++) {
                if (scores[j] > max && !containing(topArray, j)) {
                    max = scores[j];
                    maxIndex = j;
                }
            }

            topArray[i] = maxIndex;
        }

        return topArray;
    }

    public static String[] namesToArray() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("elements");

            Node names = list.item(1);

            if (names.getNodeType() == Node.ELEMENT_NODE) {

                String id = names.getAttributes().getNamedItem("id").getTextContent();
                if ("1000".equals(id.trim())) {
                    Element element = (Element) names;
                    NodeList ChildNodes = names.getChildNodes();

                    String[] array = new String[ChildNodes.getLength()];

                    for (int i = 0; i < ChildNodes.getLength() / 2; i++) {
                        String str = "name" + i;

                        String name = element.getElementsByTagName(str).item(0).getTextContent();

                        array[i] = name;


                    }

                    return array;

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int[] scoresToArray() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("elements");

            Node scores = list.item(1);

            if (scores.getNodeType() == Node.ELEMENT_NODE) {

                String id = scores.getAttributes().getNamedItem("id").getTextContent();
                if ("1001".equals(id.trim())) {
                    Element element = (Element) scores;
                    NodeList ChildNodes = scores.getChildNodes();

                    int[] array = new int[ChildNodes.getLength()];

                    for (int i = 0; i < ChildNodes.getLength() / 2; i++) {
                        String str = "score" + i;

                        String strScore = element.getElementsByTagName(str).item(0).getTextContent();

                        int score = Integer.parseInt(strScore);

                        array[i] = score;


                    }

                    return array;

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void addScore(String name, int score) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            NodeList list = doc.getElementsByTagName("elements");

            int index = findIndex(name);

            if (!(index >= 0)) {
                newScore(name, score);
                return;
            }

            int currentScore = getScore(name);
            String str = "score" + index;
            int newScore = currentScore + score;

            Node scoreList = list.item(1);

            if (scoreList.getNodeType() == Node.ELEMENT_NODE) {

                String id = scoreList.getAttributes().getNamedItem("id").getTextContent();
                if ("1001".equals(id.trim())) {
                    NodeList childNodes = scoreList.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                            if (str.equalsIgnoreCase(item.getNodeName())) {
                                item.setTextContent(String.valueOf(newScore));
                            }
                        }
                    }
                }
            }

            try (FileOutputStream output = new FileOutputStream("C:\\Users\\andre\\IdeaProjects\\p1---more-rice\\scores.xml")) {
                writeXml(doc, output);
            }

        } catch (ParserConfigurationException | SAXException
                | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static int getScore(String name) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("elements");

            Node node = list.item(1);

            int index = findIndex(name);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                NodeList ChildNodes = node.getChildNodes();

                String str = "score" + index;

                String strScore = element.getElementsByTagName(str).item(0).getTextContent();

                int score = Integer.parseInt(strScore);

                return score;
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int findIndex(String name) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("elements");

            Node node = list.item(0);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                NodeList ChildNodes = node.getChildNodes();

                for (int i = 0; i < ChildNodes.getLength(); i++) {
                    String str = "name" + i;
                    String tempName = element.getElementsByTagName(str).item(0).getTextContent();
                    if (tempName.equals(name)) {
                        return i;
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void newScore(String name, int score) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = new FileInputStream(FILENAME)) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            NodeList elements = doc.getElementsByTagName("elements");

            for (int i = 0; i < elements.getLength(); i++){
                Node element = elements.item(i);
                if (element.getNodeType() == Node.ELEMENT_NODE) {
                    String id = element.getAttributes().getNamedItem("id").getTextContent();
                    if ("1000".equals(id.trim())) {

                        NodeList childNodes = element.getChildNodes();
                        int length = childNodes.getLength();
                        String elementName = "name" + (length + 1);

                        Element player = doc.createElement(elementName);
                        player.appendChild(doc.createTextNode(name));
                        element.appendChild(player);

                    }
                    if ("1001".equals(id.trim())) {

                        NodeList childNodes = element.getChildNodes();
                        int length = childNodes.getLength();
                        String elementName = "score" + (length + 1);
                        String strScore = String.valueOf(score);

                        Element player = doc.createElement(elementName);
                        player.appendChild(doc.createTextNode(strScore));
                        element.appendChild(player);

                    }
                }
            }

            try (FileOutputStream output = new FileOutputStream(".\\scores.xml")) {
                writeXml(doc, output);
            }

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

}
