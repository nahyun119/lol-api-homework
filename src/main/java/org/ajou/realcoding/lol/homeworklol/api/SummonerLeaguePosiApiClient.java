package org.ajou.realcoding.lol.homeworklol.api;


import org.ajou.realcoding.lol.homeworklol.domain.LeagueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SummonerLeaguePosiApiClient { //api를 통해  얻은 id로 소환사의 게임 성적을 얻는 api client
    private final String appid = "RGAPI-6c0cb8cd-0bb0-49a8-a50c-9d65c424edd7";
    private final String summonerPositionUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{summonerid}?api_key={appid}";


    @Autowired
    RestTemplate restTemplate;
    //league 성적 정보는 riot 홈페이지에서 set 즉, 여러개 나와서 배열로 만들어서 배열에 하나씩 가져온 정보를 읽어오도록
    public List<LeagueDTO> requestLeagueDTO(String summonerId)
    {
        ResponseEntity<List<LeagueDTO>> leagueDTOS = restTemplate.exchange(summonerPositionUrl, HttpMethod.GET,null, new ParameterizedTypeReference<List<LeagueDTO>>() {},summonerId,appid);
        List<LeagueDTO> leagueEntryDTO = leagueDTOS.getBody();
        //leagueDTOS.add(leagueDTO);*
        //leagueDTOS.add(restTemplate.exchange(summonerPositionUrl, HttpMethod.GET,null,LeagueDTO.class,summonerId,appid).getBody());*/
        return leagueEntryDTO;
    }


}