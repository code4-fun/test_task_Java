package org.eagleinvsys.test.converters.impl;

import com.opencsv.CSVWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class CsvConverter implements Converter {

  /**
   * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
   *
   * @param collectionToConvert collection to convert to CSV format
   * @param outputStream        output stream to write CSV conversion result as text to
   */
  @Override
  public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {

    ArrayList<String[]> list = fromConvertibleCollectionToListOfArraysOfStrings(collectionToConvert);

    try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
      for (String[] item : list) {
        writer.writeNext(item);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  /**
   * Converts ConvertibleCollection instance into the instance of ArrayList<String[]>, which is the appropriate
   * input parameter type of the writeAll() method of the CSVWriter class.
   *
   * @param param ConvertibleCollection instance
   * @return list of lines of future csv
   */
  private ArrayList<String[]> fromConvertibleCollectionToListOfArraysOfStrings(ConvertibleCollection param) {
    if (param.getHeaders() == null || param.getHeaders().isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<String[]> result = new ArrayList<>();

    Collection<String> headers = param.getHeaders();
    List<String> headersList = new ArrayList<>(headers);

    String[] arrayHeaders = headersList.toArray(new String[0]);
    result.add(arrayHeaders);

    Iterable<ConvertibleMessage> records = param.getRecords();
    ArrayList<String> line = new ArrayList<>();

    if (records != null) {
      for (ConvertibleMessage cm : records) {
        for (String s : headersList) {
          line.add(cm.getElement(s));
        }
        result.add(line.toArray(new String[0]));
        line.clear();
      }
    }
    return result;
  }
}