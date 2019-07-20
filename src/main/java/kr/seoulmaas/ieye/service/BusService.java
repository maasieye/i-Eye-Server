package kr.seoulmaas.ieye.service;

import kr.seoulmaas.ieye.domain.entity.Reservation;
import kr.seoulmaas.ieye.domain.repository.ReservationRepository;
import kr.seoulmaas.ieye.service.dto.bus.BusStationTimeResDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeResDto;
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

    public BusStationTimeResDto getBusStationTime(BusTimeReqDto reqDto) {
        RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
        URI timeUrl = restInfo.getBusTimeURI(reqDto);
        HttpHeaders headers = restInfo.getDefailtHeader();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<BusTimeResDto> responseEntity = restTemplate.exchange(timeUrl, HttpMethod.GET, httpEntity, BusTimeResDto.class);

        if (responseEntity.getBody() == null) {
            throw new RuntimeException("조회 결과가 존재하지 않습니다.");
        }

        return responseEntity.getBody().getBusTimeDetailDtos().stream()
                .filter(busTimeDetailDto -> busTimeDetailDto.equalStationId(reqDto.getStationId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당하는 버스 정류소가 존재하지 않습니다."))
                .getBusStationTimeResDto();
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
}
