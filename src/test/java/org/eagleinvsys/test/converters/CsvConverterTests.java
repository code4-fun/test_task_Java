package org.eagleinvsys.test.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.eagleinvsys.test.converters.impl.ConvertibleCollectionImpl;
import org.eagleinvsys.test.converters.impl.ConvertibleMessageImpl;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.junit.jupiter.api.Test;

public class CsvConverterTests {
  @Test
  public void shouldReturnCsvTest() {
    ConvertibleMessage recordOne = new ConvertibleMessageImpl(
        new HashMap<String, String>() {{
          put("headerOne", "one");
          put("headerTwo", "two");
        }});

    ConvertibleMessage recordTwo = new ConvertibleMessageImpl(
        new HashMap<String, String>() {{
          put("headerOne", "three");
          put("headerTwo", "four");
        }});

    Iterable<ConvertibleMessage> records = new ArrayList<ConvertibleMessage>() {{
      add(recordOne);
      add(recordTwo);
    }};
    Collection<String> headers = new ArrayList<String>() {{
      add("headerOne");
      add("headerTwo");
    }};
    ConvertibleCollection collection = new ConvertibleCollectionImpl(headers, records);

    CsvConverter csvConverter = new CsvConverter();
    // Can be directed to the file or elsewhere.
    // OutputStream stream = new FileOutputStream(Paths.get("").toAbsolutePath().toString() + File.separator + "result.csv");
    OutputStream stream = new ByteArrayOutputStream();
    csvConverter.convert(collection, stream);  //

    String expected = "\"headerOne\",\"headerTwo\"\n" +
        "\"one\",\"two\"\n" +
        "\"three\",\"four\"\n";
    String actual = stream.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnEmptyStringTest() {
    Iterable<ConvertibleMessage> records = null;
    Collection<String> headers = null;
    ConvertibleCollection collection = new ConvertibleCollectionImpl(headers, records);

    CsvConverter csvConverter = new CsvConverter();
    OutputStream stream = new ByteArrayOutputStream();
    csvConverter.convert(collection, stream);

    String expected = "";
    String actual = stream.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnCsvWithOnlyHeadersTest() {
    Iterable<ConvertibleMessage> records = new ArrayList<ConvertibleMessage>();
    Collection<String> headers = new ArrayList<String>() {{
      add("headerOne");
      add("headerTwo");
    }};
    ConvertibleCollection collection = new ConvertibleCollectionImpl(headers, records);

    CsvConverter csvConverter = new CsvConverter();
    OutputStream stream = new ByteArrayOutputStream();
    csvConverter.convert(collection, stream);

    String expected = "\"headerOne\",\"headerTwo\"\n";
    String actual = stream.toString();

    assertEquals(expected, actual);
  }
}