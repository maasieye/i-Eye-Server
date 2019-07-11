package kr.seoulmaas.ieye.service.dto.path;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/***
 * Resource URI	https://api2.sktelecom.com/tmap/routes/pedestrian?version={version}
 * Protocol /HTTP Method	REST / Post Method
 * Version	1
 * Pre-Conditions	1) Request Header accept 의 값으로 다음의 값을 지원 합니다.
 *     - application/json : json 포맷의 데이터 응답. (Default)
 *     - application/xml: xml 포맷의 데이터 응답.
 *     - application/javascript : jsonp 포맷의 데이터 응답
 * OAuth	NO
 *
 * Name	    Data Type	Mandatory	Example	            Default 	Description
 * version	String	    Y	        1	                1	        API의 버전 정보입니다.
 *
 *
 * Name	    Data Type	Mandatory	Example	            Default	    Description
 * startX	Number	    Y	        126.92365493654832		        출발지 X좌표입니다.
 *                                                                  - 경도를 의미합니다.
 * startY	Number	    Y	        37.556770374096615		        출발지 Y좌표입니다.
 *                                                                  - 위도를 의미합니다.
 *
 * endX	    Number  	Y	        126.92432158129688		        목적지 X좌표입니다.
 *                                                                  - 경도를 의미합니다.
 * endY	    Number	    Y	        37.55279861528311		        목적지 Y좌표입니다.
 *                                                                  - 위도를 의미합니다.
 *
 * startName	String	Y			                                출발지 명칭입니다.
 *                                                                  - UTF-8 기반 URL인코딩으로 처리 해야 합니다.
 * endName	String	    Y			                                목적지 명칭입니다.
 *                                                                  - UTF-8 기반 URL인코딩으로 처리 해야 합니다.
 *
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkPathReqDto {

    @NotBlank
    private Long startX;

    @NotBlank
    private Long startY;

    @NotBlank
    private Long endX;

    @NotBlank
    private Long endY;

    @NotBlank
    private String startName;

    @NotBlank
    private String endName;

}
