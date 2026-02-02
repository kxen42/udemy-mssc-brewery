package org.fotm.msscbeerservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer implements BaseItem {

  @Id
  @UuidGenerator
  @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
  private UUID id;

  private String beerName;
  private String beerStyle;

  @Column(unique = true)
  private String upc;

  private BigDecimal price;
  private Integer quantityOnHand;
  private Integer minimumOnHand;
  private Integer quantityToBrew;

  // we want optimistic locking
  @Version // See https://stackoverflow.com/questions/2572566/java-jpa-version-annotation
  private Long version;

  @CreationTimestamp // see https://www.baeldung.com/hibernate-creationtimestamp-updatetimestamp
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
  private OffsetDateTime createdDate;

  @UpdateTimestamp // see https://www.baeldung.com/hibernate-creationtimestamp-updatetimestamp
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime lastModifiedDate;

}
