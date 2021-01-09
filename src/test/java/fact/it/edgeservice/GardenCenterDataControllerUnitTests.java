package fact.it.edgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.edgeservice.model.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class GardenCenterDataControllerUnitTests {

    // URL's of the services
    @Value("${gardencenterservice.baseurl}")
    private String gardenCenterServiceBaseUrl;
    @Value("${plantservice.baseurl}")
    private String plantServiceBaseUrl;
    @Value("${employeeservice.baseurl}")
    private String employeeServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    private Plant plantCenter1Plant1 = new Plant(1, "P0100", "Kerstroos", "De Helleborus niger staat beter bekend als de kerstroos. Deze tuinplant dankt die bijnaam onder andere aan zijn bijzondere bloeiperiode. Wanneer de rest van de tuin in diepe winterrust is, bloeit de kerstroos namelijk helemaal op. Volgens een oude legende zou de eerste kerstroos in Bethlehem zijn ontsproten uit de tranen van een arme herder die geen geschenk had voor het kindje Jezus. Op die manier kon hij de bloemen geven.");
    private Plant plantCenter1Plant2 = new Plant(1, "P0101", "Hortensia", "Het zal je waarschijnlijk niet verbazen de hortensia in de lijst tegen te komen. Deze plant kom je in veel Nederlandse voor- en achtertuinen tegen. De hortensia kent veel verschillende soorten, maar de populairste is toch wel de Hydrangea macrophylla.");
    private Plant plantCenter2Plant1 = new Plant(2, "P0102", "Buxus", "De buxus is zo'n fijne plant, omdat je deze in werkelijk alle vormen kunt snoeien waarin je maar wilt. Hierdoor zijn ze in iedere tuin precies naar de wensen van de eigenaar te gebruiken.");
    private Plant plantCenter3Plant2 = new Plant(3, "P0103", "Klimop", "Ondanks dat sommigen hem liever kwijt zijn dan rijk, is de klimop in Nederland een erg populaire plant. Waarschijnlijk heeft de plant deze status te danken aan het feit dat het een makkelijke plant is die het overal goed doet. Ook blijft de Hedera het hele jaar door groen en is het een geschikte plant om het gevoel van een verticale tuin mee te creëren.");
    private Plant plantCenter3Plant3 = new Plant(3, "P0104", "Hartlelie", "Door zijn vele soorten is er wat de hartlelie betreft voor iedereen wel een passende variant te vinden. De Hosta is bijvoorbeeld te krijgen met goudgele, witte, roomwit omrande, groene of blauwe (zweem) bladeren.");

    private List<Plant> allPlants = Arrays.asList(plantCenter1Plant1, plantCenter1Plant2, plantCenter2Plant1, plantCenter3Plant2, plantCenter3Plant3);
    private List<Plant> descriptionGroenPlants = Arrays.asList(plantCenter3Plant2, plantCenter3Plant3); // This are the plants that are expected when description containing "groen"

    @BeforeEach
    public void initializeMockServer() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void whenGetPlants_thenReturnAllJsonPlants() throws Exception {

        // GET all plants
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allPlants))
                );

        mockMvc.perform(get("/plants"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(1)))
                .andExpect(jsonPath("$[0].plantNumber", is("P0100")))
                .andExpect(jsonPath("$[0].name", is("Kerstroos")))
                .andExpect(jsonPath("$[0].description", is("De Helleborus niger staat beter bekend als de kerstroos. Deze tuinplant dankt die bijnaam onder andere aan zijn bijzondere bloeiperiode. Wanneer de rest van de tuin in diepe winterrust is, bloeit de kerstroos namelijk helemaal op. Volgens een oude legende zou de eerste kerstroos in Bethlehem zijn ontsproten uit de tranen van een arme herder die geen geschenk had voor het kindje Jezus. Op die manier kon hij de bloemen geven.")))
                .andExpect(jsonPath("$[1].gardenCenterId", is(1)))
                .andExpect(jsonPath("$[1].plantNumber", is("P0101")))
                .andExpect(jsonPath("$[1].name", is("Hortensia")))
                .andExpect(jsonPath("$[1].description", is("Het zal je waarschijnlijk niet verbazen de hortensia in de lijst tegen te komen. Deze plant kom je in veel Nederlandse voor- en achtertuinen tegen. De hortensia kent veel verschillende soorten, maar de populairste is toch wel de Hydrangea macrophylla.")))
                .andExpect(jsonPath("$[2].gardenCenterId", is(2)))
                .andExpect(jsonPath("$[2].plantNumber", is("P0102")))
                .andExpect(jsonPath("$[2].name", is("Buxus")))
                .andExpect(jsonPath("$[2].description", is("De buxus is zo'n fijne plant, omdat je deze in werkelijk alle vormen kunt snoeien waarin je maar wilt. Hierdoor zijn ze in iedere tuin precies naar de wensen van de eigenaar te gebruiken.")))
                .andExpect(jsonPath("$[3].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[3].plantNumber", is("P0103")))
                .andExpect(jsonPath("$[3].name", is("Klimop")))
                .andExpect(jsonPath("$[3].description", is("Ondanks dat sommigen hem liever kwijt zijn dan rijk, is de klimop in Nederland een erg populaire plant. Waarschijnlijk heeft de plant deze status te danken aan het feit dat het een makkelijke plant is die het overal goed doet. Ook blijft de Hedera het hele jaar door groen en is het een geschikte plant om het gevoel van een verticale tuin mee te creëren.")))
                .andExpect(jsonPath("$[4].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[4].plantNumber", is("P0104")))
                .andExpect(jsonPath("$[4].name", is("Hartlelie")))
                .andExpect(jsonPath("$[4].description", is("Door zijn vele soorten is er wat de hartlelie betreft voor iedereen wel een passende variant te vinden. De Hosta is bijvoorbeeld te krijgen met goudgele, witte, roomwit omrande, groene of blauwe (zweem) bladeren.")));
    }

    @Test
    public void whenGetPlantByName_thenReturnJsonPlant() throws Exception {

        // GET the plant by name
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants/name/Kerstroos")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(plantCenter1Plant1))
                );

        mockMvc.perform(get("/plants/name/{name}", "Kerstroos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gardenCenterId", is(1)))
                .andExpect(jsonPath("$.plantNumber", is("P0100")))
                .andExpect(jsonPath("$.name", is("Kerstroos")))
                .andExpect(jsonPath("$.description", is("De Helleborus niger staat beter bekend als de kerstroos. Deze tuinplant dankt die bijnaam onder andere aan zijn bijzondere bloeiperiode. Wanneer de rest van de tuin in diepe winterrust is, bloeit de kerstroos namelijk helemaal op. Volgens een oude legende zou de eerste kerstroos in Bethlehem zijn ontsproten uit de tranen van een arme herder die geen geschenk had voor het kindje Jezus. Op die manier kon hij de bloemen geven.")));
    }

    @Test
    public void whenGetPlantByContainingDescription_thenReturnJsonPlants() throws Exception {

        // GET plants when in description
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants/description/groen")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(descriptionGroenPlants))
                );

        mockMvc.perform(get("/plants/description/{description}", "groen"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[0].plantNumber", is("P0103")))
                .andExpect(jsonPath("$[0].name", is("Klimop")))
                .andExpect(jsonPath("$[0].description", is("Ondanks dat sommigen hem liever kwijt zijn dan rijk, is de klimop in Nederland een erg populaire plant. Waarschijnlijk heeft de plant deze status te danken aan het feit dat het een makkelijke plant is die het overal goed doet. Ook blijft de Hedera het hele jaar door groen en is het een geschikte plant om het gevoel van een verticale tuin mee te creëren.")))
                .andExpect(jsonPath("$[1].gardenCenterId", is(3)))
                .andExpect(jsonPath("$[1].plantNumber", is("P0104")))
                .andExpect(jsonPath("$[1].name", is("Hartlelie")))
                .andExpect(jsonPath("$[1].description", is("Door zijn vele soorten is er wat de hartlelie betreft voor iedereen wel een passende variant te vinden. De Hosta is bijvoorbeeld te krijgen met goudgele, witte, roomwit omrande, groene of blauwe (zweem) bladeren.")));
    }

    @Test
    public void whenGetPlantByPlantNumber_thenReturnJsonPlant() throws Exception {

        // GET the plant by the plantNumber
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants/P0102")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(plantCenter2Plant1))
                );

        mockMvc.perform(get("/plants/{plantNumber}", "P0102"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gardenCenterId", is(2)))
                .andExpect(jsonPath("$.plantNumber", is("P0102")))
                .andExpect(jsonPath("$.name", is("Buxus")))
                .andExpect(jsonPath("$.description", is("De buxus is zo'n fijne plant, omdat je deze in werkelijk alle vormen kunt snoeien waarin je maar wilt. Hierdoor zijn ze in iedere tuin precies naar de wensen van de eigenaar te gebruiken.")));
    }

    @Test
    public void whenAddPlant_thenReturnJsonPlant() throws Exception {

        Plant plantCenter2Plant5 = new Plant(2, "P0105", "Ice Dance", "De Carex morrowii 'Ice Dance' is een laagblijvend siergras dat in de breedte groeit met schuin opgaande bladeren. De 'Ice Dance' wordt max. 40cm hoog worden.");

        // POST new plant
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(plantCenter2Plant5))
                );

        mockMvc.perform(post("/plants")
                .param("gardenCenterId", plantCenter2Plant5.getGardenCenterId() + "")
                .param("plantNumber", plantCenter2Plant5.getPlantNumber())
                .param("name", plantCenter2Plant5.getName())
                .param("description", plantCenter2Plant5.getDescription())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gardenCenterId", is(2)))
                .andExpect(jsonPath("$.plantNumber", is("P0105")))
                .andExpect(jsonPath("$.name", is("Ice Dance")))
                .andExpect(jsonPath("$.description", is("De Carex morrowii 'Ice Dance' is een laagblijvend siergras dat in de breedte groeit met schuin opgaande bladeren. De 'Ice Dance' wordt max. 40cm hoog worden.")));
    }

//    @Test
//    public void whenUpdatePlant_thenReturnjsonPlant() throws Exception {
//
//        Plant updatedPlantCenter2Plant1 = new Plant(2, "P0102", "BuxusUPDATE", "De buxus is zo'n fijne plant, omdat je deze in werkelijk alle vormen kunt snoeien waarin je maar wilt. Hierdoor zijn ze in iedere tuin precies naar de wensen van de eigenaar te gebruiken.");
//
//        // GET plant that is going to be updated
//        mockServer.expect(ExpectedCount.once(),
//                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants/P0102")))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(mapper.writeValueAsString(plantCenter2Plant1)) // The original buxus
//                );
//
//        // PUT the changed plant
////        mockServer.expect(ExpectedCount.once(),
////                requestTo(new URI("http://" + plantServiceBaseUrl + "/plants")))
////                .andExpect(method(HttpMethod.PUT))
////                .andRespond(withStatus(HttpStatus.OK)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .body(mapper.writeValueAsString(updatedPlantCenter2Plant1)) // The updated buxus
////                );
//
//        mockMvc.perform(put("/plants")
//                .param("gardenCenterId", updatedPlantCenter2Plant1.getGardenCenterId() + "")
//                .param("plantNumber", updatedPlantCenter2Plant1.getPlantNumber())
//                .param("name", updatedPlantCenter2Plant1.getName())
//                .param("description", updatedPlantCenter2Plant1.getDescription())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.gardenCenterId", is(2)))
//                .andExpect(jsonPath("$.plantNumber", is("P0102")))
//                .andExpect(jsonPath("$.name", is("BuxusUPDATE")))
//                .andExpect(jsonPath("$.description", is("De buxus is zo'n fijne plant, omdat je deze in werkelijk alle vormen kunt snoeien waarin je maar wilt. Hierdoor zijn ze in iedere tuin precies naar de wensen van de eigenaar te gebruiken.")));
//    }

}
