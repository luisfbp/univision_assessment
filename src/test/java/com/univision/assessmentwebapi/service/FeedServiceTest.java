package com.univision.assessmentwebapi.service;

import com.univision.assessmentwebapi.UnivisionTestApplicationTests;
import com.univision.assessmentwebapi.client.FeedRESTClient;
import com.univision.assessmentwebapi.dto.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UnivisionTestApplicationTests.class })
public class FeedServiceTest {

    @Autowired
    private FeedService feedService;

    @MockBean
    private FeedRESTClient feedRESTClient;

    @Before
    public void setUp () {
        final FeedResponseDTO feedResponseDTO = stubFeedSummaryResponseDTO();
        Mockito.when(feedRESTClient.getFeeds(anyString())).thenReturn(feedResponseDTO);
    }

    @Test
    public void getFeedSummaryTest_success() {

        FeedSummaryResponseDTO feedSummaryResponse = feedService.getFeedSummary("http://fake.url.com");

        assertNotNull("Response cannot be null",feedSummaryResponse);
        assertEquals("Must be 3 and only 3 types", 3, feedSummaryResponse.getSummary().size());

        FeedSummaryDTO summarySlideshow = findFeedSummaryDTOsByType("slideshow", feedSummaryResponse);

        assertNotNull("Must exist slideshows types", summarySlideshow);
        assertEquals("Must be only 2 contents with type slideshow", 2, summarySlideshow.getTitles().size());

        FeedSummaryDTO summaryArticle = findFeedSummaryDTOsByType("article", feedSummaryResponse);

        assertNotNull("Must exist articles types", summaryArticle);
        assertEquals("Must be only 5 contents with type article", 5, summaryArticle.getTitles().size());

        FeedSummaryDTO summaryVideo = findFeedSummaryDTOsByType("video", feedSummaryResponse);

        assertNotNull("Must exist a video type", summaryVideo);
        assertEquals("Must be only a content with type video", 1, summaryVideo.getTitles().size());

    }

    private FeedSummaryDTO findFeedSummaryDTOsByType(String type,FeedSummaryResponseDTO feedSummaryResponse) {
        return feedSummaryResponse.getSummary().stream()
                .filter(s -> type.equals(s.getType()))
                .findAny()
                .orElse(null);
    }

    /**
     * stub a FeedSummaryResponseDTO object with the following structure
     *
     * {
     *     "data" : {
     *         "widgets":[
     *           {
     *             "contents":[
     *               {
     *                   "type":"slideshow",
     *                   "title":"Con una corona de 16 libras el nuevo monarca de Tailandia asciende al trono"
     *               },
     *               {
     *                   "type":"video",
     *                   "title":"Miles de personas se toman las calles de México para marchar en contra de AMLO"
     *               },
     *               {
     *                   "type":"article",
     *                   "title":"Autoridades reportan que 41 personas murieron tras el incendio en un avión de la aerolínea rusa Aeroflot"
     *               },
     *               {
     *                   "type":"article",
     *                   "title":"Pence dice que oleada migratoria es “una crisis de inmigración ilegal”, no de migrantes que huyen"
     *               }
     *             ]
     *           },
     *           {
     *             "contents":[
     *               {
     *                   "type":"slideshow",
     *                   "title":"Represión, caos y heridos: las imágenes de los enfrentamientos en Caracas"
     *               },
     *               {
     *                   "type":"article",
     *                   "title":"Trump se opone a que el fiscal especial Mueller testifique ante el Congreso"
     *               },
     *               {
     *                   "type":"article",
     *                   "title":"Trump nombra a ex jefe de la Patrulla Fronteriza como director del Servicio de Control de Inmigración y Aduanas (ICE)"
     *               }
     *             ]
     *           },
     *           {
     *             "contents":[
     *               {
     *                   "type":"article",
     *                   "title":"Los secretos de las mucamas de Trump"
     *               }
     *             ]
     *           }
     *         ]
     *     }
     * }
     *
     * @return
     */
    private FeedResponseDTO stubFeedSummaryResponseDTO() {

        ContentDTO contentSlideshow1 = new ContentDTO("slideshow","Con una corona de 16 libras el nuevo monarca de Tailandia asciende al trono");
        ContentDTO contentSlideshow2 = new ContentDTO("slideshow","Represión, caos y heridos: las imágenes de los enfrentamientos en Caracas");

        ContentDTO contentVideo1 = new ContentDTO("video","Miles de personas se toman las calles de México para marchar en contra de AMLO");

        ContentDTO contentArticle1 = new ContentDTO("article","Los secretos de las mucamas de Trump");
        ContentDTO contentArticle2 = new ContentDTO("article","Autoridades reportan que 41 personas murieron tras el incendio en un avión de la aerolínea rusa Aeroflot");
        ContentDTO contentArticle3 = new ContentDTO("article","Pence dice que oleada migratoria es “una crisis de inmigración ilegal”, no de migrantes que huyen");
        ContentDTO contentArticle4 = new ContentDTO("article","Trump nombra a ex jefe de la Patrulla Fronteriza como director del Servicio de Control de Inmigración y Aduanas (ICE)");
        ContentDTO contentArticle5 = new ContentDTO("article","Trump se opone a que el fiscal especial Mueller testifique ante el Congreso");

        WidgetDTO widget1 = new WidgetDTO(Arrays.asList(contentSlideshow1, contentVideo1, contentArticle2, contentArticle3));
        WidgetDTO widget2 = new WidgetDTO(Arrays.asList(contentSlideshow2, contentArticle5, contentArticle4));
        WidgetDTO widget3 = new WidgetDTO(Collections.singletonList(contentArticle1));
        WidgetDTO widget4 = new WidgetDTO(Collections.emptyList());


        final List<WidgetDTO> widgetList = Arrays.asList(widget1,widget2,widget3,widget4);

        final FeedDataDTO feedData = new FeedDataDTO(widgetList);

        return new FeedResponseDTO(feedData);
    }

}
