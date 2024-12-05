package se.ifmo.ru.firstservice.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import se.ifmo.ru.firstservice.model.MusicBand;

import java.util.ArrayList;
import java.util.List;

public class StringToObjectUsingJackson {

    public static List<MusicBand> convertStringToListOfObject(String xmlString) throws Exception {
        var inputs = splitList(xmlString);
        XmlMapper xmlMapper = new XmlMapper();
        var resp = new ArrayList<MusicBand>();
        for (var inp : inputs) {
            if (inp.contains("name")) {
                resp.add(xmlMapper.readValue(inp, MusicBand.class));
            }
        }
        return resp;
    }

    public static List<String> splitList(String response) {
        var respList =  response.split("</item>");
        var omg = new ArrayList<String>();
        for (var resp : respList) {
            resp = "<MusicBand>" + resp + "</MusicBand>";
            resp = resp.replace("<List>", "").replace("</List>", "").replace("<item>", "");

            omg.add(resp);
        }
        return omg;
    }
}
