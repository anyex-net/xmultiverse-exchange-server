package com.anyex.exchange.binance.bean.event;

import com.anyex.exchange.binance.bean.account.UserAssets;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Custom deserializer for an AssetBalance, since the streaming API returns an object in the format {"a":"symbol","f":"free","l":"locked"},
 * which is different than the format used in the REST API.
 */
public class MarginAssetBalanceDeserializer extends JsonDeserializer<UserAssets> {

  @Override
  public UserAssets deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    final String asset = node.get("a").asText();
    final String free = node.get("f").asText();
    final String locked = node.get("l").asText();
    // TODO 这里的两个参数待确定
    final String b = node.get("b")==null?"0":node.get("b").asText();
    final String i = node.get("i")==null?"0":node.get("i").asText();

    UserAssets userAssets = new UserAssets();
    userAssets.setAsset(asset);
    userAssets.setFree(free);
    userAssets.setLocked(locked);
    userAssets.setBorrowed(b);
    userAssets.setInterest(i);
    return userAssets;
  }
}