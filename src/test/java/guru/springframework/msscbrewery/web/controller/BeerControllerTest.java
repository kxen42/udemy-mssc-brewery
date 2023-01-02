package guru.springframework.msscbrewery.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @Before
    public void setUp() {
        validBeer = BeerDto.builder()
                           .id(UUID.randomUUID())
                           .beerName("Beer1")
                           .beerStyle("PALE_ALE")
                           .upc(123456789012L)
                           .build();
    }

    @Test
    public void getBeer() throws Exception {
        // test all values to make sure @RequestBody was handled right
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId()
                                                       .toString())
                            .accept(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().encoding(StandardCharsets.UTF_8.toString()))
               .andExpect(jsonPath("$.id", is(validBeer.getId()
                                                       .toString())))
               .andExpect(jsonPath("$.beerName", is("Beer1")))
               .andExpect(jsonPath("$.beerStyle", is("PALE_ALE")))
               .andExpect(jsonPath("$.upc", is(123456789012L)));
    }


    @Test
    public void createNewBear() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        BeerDto savedDto = BeerDto.builder()
                                  .id(UUID.randomUUID())
                                  .beerName("New Beer")
                                  .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(beerDtoJson))
               .andExpect(status().isCreated());
    }

    @Test
    public void updateBeer() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put("/api/v1/beer/" + validBeer.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(beerDtoJson))
               .andExpect(status().isNoContent());

        then(beerService).should()
                         .updateBeer(any(), any());
    }
}