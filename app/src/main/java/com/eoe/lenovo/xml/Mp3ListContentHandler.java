package com.eoe.lenovo.xml;

import com.eoe.lenovo.model.Mp3Info;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * Created by Lenovo on 2016/4/28.
 */
public class Mp3ListContentHandler extends DefaultHandler {
    private List<Mp3Info> mp3Infos = null;
    private Mp3Info mp3Info = null;
    private String tagName = null;

    //添加一个该类的构造函数
    public Mp3ListContentHandler(List<Mp3Info> mp3Infos) {
        super();
        this.mp3Infos = mp3Infos;
    }

    public List<Mp3Info> getMp3Infos() {
        return mp3Infos;
    }

    public void setMp3Infos(List<Mp3Info> mp3Infos) {
        this.mp3Infos = mp3Infos;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        super.characters(ch, start, length);
        String temp = new String(ch, start, length);
        if (tagName.equals("id")) {
            mp3Info.setId(temp);
        } else if (tagName.equals("mp3.name")) {
            mp3Info.setMp3Name(temp);
        } else if (tagName.equals("mp3.size")) {
            mp3Info.setMp3Size(temp);
        } else if (tagName.equals("lrc.name")) {
            mp3Info.setLrcName(temp);
        } else if (tagName.equals("mp3.name")) {
            mp3Info.setLrcSize(temp);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        super.startElement(uri, localName, qName, attributes);
        this.tagName = localName;
        if (localName.equals("resource")) {
            mp3Info = new Mp3Info();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        super.endElement(uri, localName, qName);
        System.out.println("-----------------");
        if (qName.equals("resource")) {
            mp3Infos.add(mp3Info);
        }
        tagName = "";
    }
}
