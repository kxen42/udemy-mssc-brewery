package org.fotm.msscbrewery.web.model.v2;

import lombok.Getter;

@Getter
public enum BeerStyleEnum {
  LAGER("Lager"),
  PILSNER("Pilsner"),
  STOUT("Stout"),
  GOSE("Gose"),
  PORTER("Porter"),
  ALE("Ale"),
  WHEAT("Wheat"),
  IPA("IPA"),
  PALE_ALE("Pale Ale"),
  SAISON("Saison"),
  AMBER_LAGER( "Amber Lager"),
  DARK_LAGER( "Dark Lager"),
  BLACK_LAGER( "Black Lager");
  
  private final String style;
  BeerStyleEnum(String style) {
    this.style = style;
  }


  public static BeerStyleEnum fromStyle(String style) {
    for (BeerStyleEnum b : BeerStyleEnum.values()) {
      if (b.style.equalsIgnoreCase(style)) {
        return b;
      }
    }
    return null;
  }
}
