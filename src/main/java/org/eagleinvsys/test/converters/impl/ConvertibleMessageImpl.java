package org.eagleinvsys.test.converters.impl;

import java.util.Map;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleMessageImpl implements ConvertibleMessage {
  private final Map<String, String> record;

  public ConvertibleMessageImpl(Map<String, String> record) {
    this.record = record;
  }

  @Override
  public String getElement(String elementId) {
    return record.getOrDefault(elementId, "");
  }
}