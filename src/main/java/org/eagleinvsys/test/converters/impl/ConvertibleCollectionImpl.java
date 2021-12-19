package org.eagleinvsys.test.converters.impl;

import java.util.Collection;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleCollectionImpl implements ConvertibleCollection {
  Collection<String> headers;
  Iterable<ConvertibleMessage> records;

  public ConvertibleCollectionImpl(Collection<String> headers, Iterable<ConvertibleMessage> records) {
    this.headers = headers;
    this.records = records;
  }

  @Override
  public Collection<String> getHeaders() {
    return headers;
  }

  @Override
  public Iterable<ConvertibleMessage> getRecords() {
    return records;
  }
}