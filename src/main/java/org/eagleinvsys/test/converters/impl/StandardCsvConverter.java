package org.eagleinvsys.test.converters.impl;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;
import org.eagleinvsys.test.converters.StandardConverter;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class StandardCsvConverter implements StandardConverter {

  private final CsvConverter csvConverter;

  public StandardCsvConverter(CsvConverter csvConverter) {
    this.csvConverter = csvConverter;
  }

  /**
   * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
   *
   * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
   * @param outputStream        output stream to write CSV conversion result as text to
   */
  @Override
  public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {
      if (collectionToConvert == null || collectionToConvert.isEmpty() || outputStream == null) {
          return;
      }

    List<String> headers = new ArrayList<>(collectionToConvert.get(0).keySet());

    List<ConvertibleMessage> records = collectionToConvert.stream()
        .map(ConvertibleMessageImpl::new).collect(Collectors.toList());

    ConvertibleCollection convertibleCollection = new ConvertibleCollectionImpl(headers, records);
    csvConverter.convert(convertibleCollection, outputStream);
  }
}