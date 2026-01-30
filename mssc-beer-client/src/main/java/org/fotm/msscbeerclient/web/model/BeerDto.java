package org.fotm.msscbeerclient.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

  private UUID id;
  private Long version;
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;
  private String beerName;
  private String beerStyle;
  private String upc;
  private BigDecimal price;
  private Integer quantityOnHand;

}
