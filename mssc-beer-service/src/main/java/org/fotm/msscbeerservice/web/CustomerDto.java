package org.fotm.msscbeerservice.web;

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
public class CustomerDto implements BaseItem {

  private UUID id;
  private Long version;
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;
  private String customerName;
}
