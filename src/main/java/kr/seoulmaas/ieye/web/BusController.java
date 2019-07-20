package kr.seoulmaas.ieye.web;

import kr.seoulmaas.ieye.service.BusService;
import kr.seoulmaas.ieye.service.dto.bus.BusStationTimeResDto;
import kr.seoulmaas.ieye.service.dto.bus.BusTimeReqDto;
import kr.seoulmaas.ieye.service.dto.bus.remain.BusRemainInfoReqDto;
import kr.seoulmaas.ieye.service.dto.bus.remain.BusRemainInfoResDto;
import kr.seoulmaas.ieye.service.dto.reservation.ReservationSaveReqDto;
import kr.seoulmaas.ieye.service.dto.reservation.ReservationUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bus")
public class BusController {

    private final BusService busService;

    @PostMapping
    public ResponseEntity<BusStationTimeResDto> getArriveBusTime(@RequestBody BusTimeReqDto reqDto) {
        return ResponseEntity.ok(busService.getBusStationTime(reqDto));
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> saveBusReservation(@RequestBody ReservationSaveReqDto reqDto) {
        busService.saveReservation(reqDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reserve")
    public ResponseEntity<Void> updateReservation(@RequestBody ReservationUpdateReqDto reqDto) {
        busService.updateReservation(reqDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/reserve")
    public ResponseEntity<Void> removeReservation(@RequestBody ReservationUpdateReqDto reqDto) {
        busService.removeReservation(reqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remain")
    public ResponseEntity<BusRemainInfoResDto> getRemainBus(@RequestBody BusRemainInfoReqDto reqDto) {

        return ResponseEntity.ok(new BusRemainInfoResDto());
    }

}
