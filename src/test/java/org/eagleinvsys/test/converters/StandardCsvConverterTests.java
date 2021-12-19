package org.eagleinvsys.test.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Test;

class StandardCsvConverterTests {
  @Test
  public void shouldReturnCsvTest() {
    ArrayList<Map<String, String>> collection = new ArrayList<Map<String, String>>() {{
      add(new HashMap<String, String>() {{
        put("headerOne", "one");
        put("headerTwo", "two");
      }});
      add(new HashMap<String, String>() {{
        put("headerOne", "three");
        put("headerTwo", "four");
      }});
    }};

    CsvConverter csvConverter = new CsvConverter();
    StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);

    OutputStream stream = new ByteArrayOutputStream();
    standardCsvConverter.convert(collection, stream);

    String expected = "\"headerOne\",\"headerTwo\"\n" +
        "\"one\",\"two\"\n" +
        "\"three\",\"four\"\n";
    String actual = stream.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnEmptyStringTest() {
    ArrayList<Map<String, String>> collection = new ArrayList<Map<String, String>>();

    CsvConverter csvConverter = new CsvConverter();
    StandardCsvConverter standardCsvConverter = new StandardCsvConverter(csvConverter);

    OutputStream stream = new ByteArrayOutputStream();
    standardCsvConverter.convert(collection, stream);

    String expected = "";
    String actual = stream.toString();

    assertEquals(expected, actual);
  }
}