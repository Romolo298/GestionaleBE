package org.example.gestionale_be.ProdottoTest;


import org.example.gestionale_be.Dto.ProdottoDto;

import org.example.gestionale_be.GestionaleBeApplicationTests;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProdottoTest extends GestionaleBeApplicationTests {

    private final static String URL_PRODOTTO = "/api/prodotto";


    @Test
    public void inserimentoProdottoTest() throws Exception {

        var risposta = post(URL_PRODOTTO + "/inserisci-modifica")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJsonString(fillObject(new ProdottoDto())));

        mockMvc.perform(risposta)
                .andExpect(status().isOk());


    }

    @Test
    public void ricercaProdottoTest() throws Exception {

        MockHttpServletRequestBuilder creazione = post(URL_PRODOTTO + "/inserisci-modifica")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJsonString(fillObject(new ProdottoDto())));

        String rispostaCreazione = mockMvc.perform(creazione)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(get(URL_PRODOTTO + "/{id}", extractIdFromResponse(rispostaCreazione))).
                andExpect(status().isOk());

    }

    @Test
    public void ricercaProdottiTest() throws Exception {

        MockHttpServletRequestBuilder creazione = post(URL_PRODOTTO + "/inserisci-modifica")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJsonString(fillObject(new ProdottoDto())));

        mockMvc.perform(creazione)
                .andExpect(status().isOk());


        mockMvc.perform(get(URL_PRODOTTO)).
                andExpect(status().isOk());

    }

    @Test
    public void creazioneProdottoPassandoId() throws Exception {

        MockHttpServletRequestBuilder creazione = post(URL_PRODOTTO + "/inserisci-modifica")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJsonString(fillObject(new ProdottoDto())));

        var x =mockMvc.perform(creazione)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(post(URL_PRODOTTO+ "/inserisci-modifica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(x)).
                andExpect(status().isBadRequest());

    }

    @Test
    public void modificaProdottoPassandoCodiceProdotto() throws Exception {

        MockHttpServletRequestBuilder creazione = post(URL_PRODOTTO + "/inserisci-modifica")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJsonString(fillObject(new ProdottoDto())));

        var x =mockMvc.perform(creazione)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(post(URL_PRODOTTO+ "/inserisci-modifica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rimuoviIdDaJson(x))).
                andExpect(status().isOk());

    }


}
