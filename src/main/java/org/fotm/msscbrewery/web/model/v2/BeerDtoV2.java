package org.fotm.msscbrewery.web.model.v2;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {

  private UUID id;
  private String beerName;
  private BeerStyleEnum beerStyle;
  private String upc;
}
