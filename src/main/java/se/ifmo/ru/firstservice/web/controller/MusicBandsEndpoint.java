package se.ifmo.ru.firstservice.web.controller;

import localhost._8080.entity.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import se.ifmo.ru.firstservice.clients.MainServiceClient;
import se.ifmo.ru.firstservice.model.Coordinates;
import se.ifmo.ru.firstservice.model.MusicGenre;
import se.ifmo.ru.firstservice.model.Nomination;
import se.ifmo.ru.firstservice.model.Studio;
import se.ifmo.ru.firstservice.service.NominationService;
import se.ifmo.ru.firstservice.util.StringToObjectUsingJackson;


@Endpoint
@Slf4j
public class MusicBandsEndpoint {

    @Autowired
    private NominationService nominationService;

    private static final String NAMESPACE_URI = "http://localhost:8080/Entity";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "NominateBandRequest")
    @ResponsePayload
    public NominateBandResponse nominateBand(@RequestPayload NominateBandRequest request) {

        var client = new MainServiceClient();
        var bandId = (long) request.getId();

        try {
            var responseFromMainService = client.get("/api/music-bands/" + bandId);

            nominationService.createNomination(Nomination.builder()
                    .bandId(bandId)
                    .genre(request.getGenre().value()).build());

            var res = new NominateBandResponse();
            res.setMessage("Группа успешно номинирована на премию");
            return res;
        } catch (Exception e) {
            var res = new NominateBandResponse();
            res.setMessage("Номинировать группу невозможно, проверьте корректность id");
            return res;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetBandsByGenre")
    @ResponsePayload
    @SneakyThrows
    public GetBandsByGenreResponse getBandsByGenre(@RequestPayload GetBandsByGenre request) {

        var client = new MainServiceClient();
        var responseFromMainService = client.get("/api/music-bands/all");
        var origBands = StringToObjectUsingJackson.convertStringToListOfObject(responseFromMainService);

        var response = new GetBandsByGenreResponse();
        var list = response.getStudio();
        for (var band : origBands) {
            if (band.getGenre().name().equals(request.getGenre().name())) {

                var soapBand = new MusicBandDto();
                soapBand.setId(band.getId());
                soapBand.setGenre(MusicGenre.getSoapVersion(band.getGenre()));
                soapBand.setName(band.getName());
                soapBand.setAlbumsCount((int) band.getAlbumsCount());
                soapBand.setNumberOfParticipants(band.getNumberOfParticipants());
                soapBand.setSinglesCount(band.getSinglesCount());
                soapBand.setCoordinates(Coordinates.getSoapVersion(band.getCoordinates()));
                soapBand.setStudio(Studio.getSoapVersion(band.getStudio()));
                list.add(soapBand);
            }
        }
        return response;
    }

}
