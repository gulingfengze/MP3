package com.eoe.lenovo.mp3player;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eoe.lenovo.download.HttpDownloader;
import com.eoe.lenovo.model.Mp3Info;
import com.eoe.lenovo.xml.Mp3ListContentHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class Mp3ListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       //执行菜单点击事件
        switch (item.getItemId()) {
            case R.id.mp3list_updata:
                String xml=downLoadXML("http://192.168.100.109:8601/mp3/resources.xml");
                parse(xml);
                break;
            case R.id.mp3list_about:

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private String downLoadXML(String urlStr){
        HttpDownloader httpDownloader=new HttpDownloader();
        String result=httpDownloader.download(urlStr);
        return result;
    }
    //解析XML数据
    private List<Mp3Info> parse(String xmlStr){
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            List<Mp3Info>mp3Infos=new ArrayList<Mp3Info>();
            Mp3ListContentHandler mp3ListContentHandler=new Mp3ListContentHandler(mp3Infos);
            //将CntentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(mp3ListContentHandler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlStr)));
            for (Iterator iterator=mp3Infos.iterator();iterator.hasNext();){
               Mp3Info mp3Info= (Mp3Info) iterator.next();
                System.out.println(mp3Info);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
