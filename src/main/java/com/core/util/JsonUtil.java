package com.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


@Slf4j
public final class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final XMLSerializer xmlTool = new XMLSerializer();
    private static final XStream xstream = new XStream();
    private static final XStream xstreamCdata = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean CDATA = true;
				
				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				protected void writeText(QuickWriter writer, String text) {
					if (CDATA) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
    
    static {
    	xstreamCdata.autodetectAnnotations(true);
    	xstream.autodetectAnnotations(true);
    }
//    private static final ObjectMapper xssSerializerObjectMapper = new AhXssSerializerObjectMapper();

    private static ObjectMapper getObjectMapper() {
    	return mapper;
//        return decodeForXss ? xssSerializerObjectMapper : mapper;
    }
    
    private static XStream getXStream(boolean cdata) {
    	return cdata ? xstreamCdata : xstream;
    }

    public static String toJsonStringRaw(final Object obj)
                    throws JsonGenerationException, JsonMappingException, IOException {
        return toJsonStringRaw(obj, false);
    }

    public static String toJsonStringRaw(final Object obj, final boolean encodeForXss)
                    throws JsonGenerationException, JsonMappingException, IOException {
        Writer sw = new StringWriter();
        getObjectMapper().writerWithDefaultPrettyPrinter().writeValue(sw, obj);
        return sw.toString();
    }

    public static <T> T toObjectRaw(final String json, final Class<T> valueType)
                    throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(json, valueType);
    }

    public static <T> T toObjectRaw(final String json, final Class<T> valueType, final boolean decodeForXss)
                    throws JsonParseException, JsonMappingException, IOException {
        return getObjectMapper().readValue(json, valueType);
    }

    public static String toJsonString(final Object obj) {
        try {
            return toJsonStringRaw(obj);
        } catch (Exception e) {
            log.error("Failed convert {} to JSON", obj, e);
        }
        return null;
    }

    public static <T> T toObject(final String json, final Class<T> valueType) {
        return toObject(json, valueType, true);
    }

    public static <T> T toObject(final String json, final Class<T> valueType, final boolean decodeForXss) {
        try {
            return toObjectRaw(json, valueType, decodeForXss);
        } catch (Exception e) {
            log.error("Failed convert JSON: {} to: {}", json, valueType, e);
        }
        return null;
    }
    
    public static <T> T toObject(String json, Class<T> valueType, Class<?>... parameterTypes) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(valueType, parameterTypes));
        }
        catch (Exception e) {
            log.error("Failed convert JSON: {} to: {}", json, valueType, e);
        }
        return null;
    }

    public static <T> List<T> toObjectList(final String json, final Class<T> valueType) {
        return toObjectList(json, valueType, true);
    }

    public static <T> List<T> toObjectList(final String json, final Class<T> valueType, final boolean decodeForXss) {
        try {
            return toObjectListRaw(json, valueType, decodeForXss);
        } catch (Exception e) {
            log.error("Failed convert JSON: {} to: List<{}>", json, valueType, e);
        }
        return null;
    }

    public static <T> List<T> toObjectListRaw(final String json, final Class<T> valueType, final boolean decodeForXss)
                    throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = getObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }
    
    public static String xmlToJsonString(final String xmlStr) {
    	String xmlRepl = xmlStr.replace("<![CDATA[]]>", "<![CDATA[null]]>");
    	JSON json = xmlTool.read(xmlRepl);
    	return json.toString(1);
    }
    
    public static <T> T xmlToObject(final String xmlStr, final Class<T> valueType) {
    	String jsonStr = xmlToJsonString(xmlStr);
        return toObject(jsonStr, valueType);
    }
    
    public static String toXmlString(Object xmlObj) {
    	return getXStream(false).toXML(xmlObj);
    }
    
    public static String toXmlString(Object xmlObj, boolean cdata) {
    	return getXStream(cdata).toXML(xmlObj);
    }
    
    private JsonUtil() {
        throw new AssertionError("JsonUtil should never be instantiated");
    }

}
