package com.anyex.exchange.binance.bean.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAssets {

  private String asset;

  private String borrowed;

  private String free;

  private String interest;

  private String locked;

  private String netAsset;

}
