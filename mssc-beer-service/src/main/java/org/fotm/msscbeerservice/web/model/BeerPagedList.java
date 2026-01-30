package org.fotm.msscbeerservice.web.model;


import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/// Pageable list of Beers
public class BeerPagedList extends PageImpl<BeerDto> {

  public BeerPagedList(List<BeerDto> content,
      Pageable pageable, long total) {
    super(content, pageable, total);
  }

  public BeerPagedList(List<BeerDto> content) {
    super(content);
  }
}
