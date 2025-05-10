package com.anyex.exchange.binance.client.domain.event;

import com.anyex.exchange.binance.client.domain.event.UserDataUpdateEvent.UserDataUpdateEventType;
import com.anyex.exchange.binance.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Custom deserializer for a User Data stream event, since the API can return four different responses in this stream.
 *
 * @see UserDataUpdateEvent
 */
public class UserDataUpdateEventDeserializer extends JsonDeserializer<UserDataUpdateEvent> {

  private ObjectMapper mapper;

  @Override
  public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {

    if (mapper == null) {
      mapper = new ObjectMapper();
    }

    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    String json = node.toString();

    final String eventTypeId = node.get("e").asText();
    final Long eventTime = node.get("E").asLong();

    UserDataUpdateEvent userDataUpdateEvent = new UserDataUpdateEvent();
    userDataUpdateEvent.setEventType(UserDataUpdateEventType.DATA);
    userDataUpdateEvent.setEventTime(eventTime);
    userDataUpdateEvent.setData(json);
/*
    if (StringUtils.equalsIgnoreCase(userDataUpdateEventType.getEventTypeId(),"FUTURES")) {
      userDataUpdateEvent.setData(json);
    }
    else if (userDataUpdateEventType == UserDataUpdateEventType.ACCOUNT_POSITION_UPDATE) {
      AccountUpdateEvent accountUpdateEvent = getUserDataUpdateEventDetail(json, AccountUpdateEvent.class, mapper);
      userDataUpdateEvent.setOutboundAccountPositionUpdateEvent(accountUpdateEvent);
    }
    else if (userDataUpdateEventType == UserDataUpdateEventType.BALANCE_UPDATE) {
      BalanceUpdateEvent balanceUpdateEvent = getUserDataUpdateEventDetail(json, BalanceUpdateEvent.class, mapper);
      userDataUpdateEvent.setBalanceUpdateEvent(balanceUpdateEvent);
    } else { // userDataUpdateEventType == UserDataUpdateEventType.ORDER_TRADE_UPDATE
      OrderTradeUpdateEvent orderTradeUpdateEvent = getUserDataUpdateEventDetail(json, OrderTradeUpdateEvent.class, mapper);
      userDataUpdateEvent.setOrderTradeUpdateEvent(orderTradeUpdateEvent);
    }*/

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
