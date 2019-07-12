package org.ajou.realcoding.lol.homeworklol.controller;


import org.ajou.realcoding.lol.homeworklol.api.SummonerIdApiClient;
import org.ajou.realcoding.lol.homeworklol.api.SummonerLeaguePosiApiClient;
import org.ajou.realcoding.lol.homeworklol.domain.LeagueDTO;
import org.ajou.realcoding.lol.homeworklol.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ajou.realcoding.lol.homeworklol.service.LolencryptedSummonerIdService;

import java.io.IOException;

@RestController
public class SummonerNameController {

    @Autowired
    private LolencryptedSummonerIdService lolencryptedSummonerIdService;
    @Autowired
    private SummonerIdApiClient summonerIdApiClient;

    @Autowired
    private SummonerLeaguePosiApiClient summonerLeaguePosiApiClient;

    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public LeagueDTO getSummonerLeaguePosition(@PathVariable String summonerName) throws IOException {
        SummonerDTO summonerDTO = summonerIdApiClient.requestSummonerDTO(summonerName);
        String summonerId = summonerDTO.getId();
        LeagueDTO leaguePosition = summonerLeaguePosiApiClient.requestLeagueDTO(summonerId);
        return leaguePosition;
        //소환사 이름을 통해서 id를 얻는 api client에 선언된 함수를 호출해서 얻어오는 함수
    }


}
