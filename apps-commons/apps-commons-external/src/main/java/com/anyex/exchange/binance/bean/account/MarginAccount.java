package com.anyex.exchange.binance.bean.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Account information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MarginAccount {

  private boolean borrowEnabled;

  private String marginLevel;

  private String totalAssetOfBtc;

  private String totalLiabilityOfBtc;

  private String totalNetAssetOfBtc;

  private boolean tradeEnabled;

  private boolean transferEnabled;

  /**
   * List of asset balances of this account.
   */
  private List<UserAssets> userAssets;


}
