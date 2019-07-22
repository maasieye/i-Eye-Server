package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.entity.Reservation;
import kr.seoulmaas.ieye.domain.repository.ReservationRepository;
import kr.seoulmaas.ieye.service.dto.bus.BusStationTimeResDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeResDto;
import kr.seoulmaas.ieye.service.dto.bus.busRoute.BusRouteResDto;
import kr.seoulmaas.ieye.service.dto.bus.remain.BusRemainInfoReqDto;
import kr.seoulmaas.ieye.service.dto.bus.remain.BusRemainInfoResDto;
import kr.seoulmaas.ieye.service.dto.busStation.NextBusStationReqDto;
import kr.seoulmaas.ieye.service.dto.busStation.NextBusStationResDto;
import kr.seoulmaas.ieye.service.dto.reservation.ReservationSaveReqDto;
import kr.seoulmaas.ieye.service.dto.reservation.ReservationUpdateReqDto;
import kr.seoulmaas.ieye.service.utill.RestInfo;
import kr.seoulmaas.ieye.service.utill.RestTemplateConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusService {

    private final RestInfo restInfo;
    private final RestTemplateConfig restTemplateConfig;
    private final ReservationRepository reservationRepository;

    /**
     * @param reqDto 정류장 id하고 버스노선 id 가지고있음
     * @return 현재 정류장으로 오는 버스의 아이디와 오기까지 걸리는 시간
     */
    public BusStationTimeResDto getBusStationTime(BusTimeReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI timeUrl = restInfo.getBusTimeURI(reqDto);
        HttpHeaders headers = restInfo.getDefaultHeader();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<BusTimeResDto> responseEntity = restTemplate.exchange(timeUrl, HttpMethod.GET, httpEntity, BusTimeResDto.class);

        validateBody(responseEntity);

        return responseEntity.getBody().getBusTimeDetailDtos().stream()
                .filter(busTimeDetailDto -> busTimeDetailDto.equalStationId(reqDto.getStationId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당하는 버스 정류소가 존재하지 않습니다."))
                .getBusStationTimeResDto();
    }

    /**
     * @param reqDto
     * @return 이번 정류장, 다음 정류장, 남은 정류장 갯수
     */
    public BusRemainInfoResDto getRemainInfo(BusRemainInfoReqDto reqDto) {
        String nowStationId = getNowBusStationId(reqDto.toNextBusStationReqDto());
        System.out.println("nowStationId : " + nowStationId);
        ResponseEntity<BusRouteResDto> responseEntity = getRemainDetail(reqDto);
        validateBody(responseEntity);
        System.out.println(responseEntity.toString());

        return responseEntity.getBody().getBusRemainInfo(nowStationId, reqDto.getEndStationId());
    }

    public ResponseEntity<BusRouteResDto> getRemainDetail(BusRemainInfoReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI routeListUrl = restInfo.getAllBusPathURI(reqDto.getBusRouteId());
        HttpEntity httpEntity = new HttpEntity(restInfo.getDefaultHeader());

        return restTemplate.exchange(routeListUrl, HttpMethod.GET, httpEntity, BusRouteResDto.class);
    }

    /**
     * @param reqDto 내가 탄 버스의 아이디, 내가 탄 버스의 노선아이디
     * @return 내가 탄 버스의 '다음 정류장 아이디'
     */
    public String getNowBusStationId(NextBusStationReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI nextBusStationUrl = restInfo.getNextBusStationIdURI(reqDto.getBusRouteId());
        HttpHeaders headers = restInfo.getDefaultHeader();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<NextBusStationResDto> responseEntity = restTemplate.exchange(nextBusStationUrl, HttpMethod.GET, httpEntity, NextBusStationResDto.class);

        validateBody(responseEntity);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ getnow");
        System.out.println(responseEntity.toString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@");
        return responseEntity.getBody().getBusItems()
                .stream()
                .filter(nextBusItem -> nextBusItem.equalBusId(reqDto.getBusId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당하는 버스 정류소가 존재하지 않습니다."))
                .getNextStationId();
    }


    public void saveReservation(ReservationSaveReqDto reqDto) {
        Reservation reservation = reservationRepository.save(reqDto.toEntity());
    }

    @Transactional
    public void updateReservation(ReservationUpdateReqDto reqDto) {
        Reservation reservation = reservationRepository.findByMobileNumberAndStatus(reqDto.getMobileNumber(), "미승차");
        reservation.takeOn();
    }

    @Transactional
    public void removeReservation(ReservationUpdateReqDto reqDto) {
        Reservation reservation = reservationRepository.findByMobileNumberAndStatus(reqDto.getMobileNumber(), "승차");
        reservation.takeOff();
    }

    private void validateBody(ResponseEntity<?> entity) {
        if (entity.getBody() == null) {
            throw new RuntimeException("결과가 없습니다.");
        }
    }
}
