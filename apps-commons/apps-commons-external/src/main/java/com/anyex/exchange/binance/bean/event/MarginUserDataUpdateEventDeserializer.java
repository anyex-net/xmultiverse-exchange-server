package com.anyex.exchange.binance.bean.event;

import com.anyex.exchange.binance.config.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Custom deserializer for a User Data stream event, since the API can return two different responses in this stream.
 * @see UserDataUpdateEvent
 */
public class MarginUserDataUpdateEventDeserializer extends JsonDeserializer<MarginUserDataUpdateEvent> {

  private ObjectMapper mapper;

  @Override
  public MarginUserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {

    if (mapper == null){
      mapper = new ObjectMapper();
    }

    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    String json = node.toString();

    final String eventTypeId = node.get("e").asText();
    final Long eventTime = node.get("E").asLong();
    MarginUserDataUpdateEvent.UserDataUpdateEventType userDataUpdateEventType = MarginUserDataUpdateEvent.UserDataUpdateEventType.fromEventTypeId(eventTypeId);

    MarginUserDataUpdateEvent userDataUpdateEvent = new MarginUserDataUpdateEvent();
    userDataUpdateEvent.setEventType(userDataUpdateEventType);
    userDataUpdateEvent.setEventTime(eventTime);

    if (userDataUpdateEventType == MarginUserDataUpdateEvent.UserDataUpdateEventType.ACCOUNT_UPDATE) {
      MarginAccountUpdateEvent accountUpdateEvent = getUserDataUpdateEventDetail(json, MarginAccountUpdateEvent.class, mapper);
      userDataUpdateEvent.setAccountUpdateEvent(accountUpdateEvent);
    }

    return userDataUpdateEvent;
  }

  public <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz, ObjectMapper mapper) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }
}