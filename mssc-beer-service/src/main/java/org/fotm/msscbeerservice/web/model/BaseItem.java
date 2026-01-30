package org.fotm.msscbeerservice.web.model;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Using {@code OffsetDateTime} an immutable representation of a date-time with an offset from
 * UTC/Greenwich in the ISO-8601 calendar system, such as 2007-12-03T10:15:30+01:00. In other words,
 * it stores all date and time fields, to precision of nanoseconds, as well as the offset from
 * GMT/UTC.
 */
public interface BaseItem {

  UUID getId();

  void setId(UUID id);

  Long getVersion();

  void setVersion(Long version);

  OffsetDateTime getCreatedDate();

  void setCreatedDate(OffsetDateTime createdDate);

  OffsetDateTime getLastModifiedDate();

  void setLastModifiedDate(OffsetDateTime lastModifiedDate);

}
