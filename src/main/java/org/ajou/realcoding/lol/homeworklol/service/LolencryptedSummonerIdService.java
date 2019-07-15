package org.ajou.realcoding.lol.homeworklol.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lol.homeworklol.api.SummonerIdApiClient;
import org.ajou.realcoding.lol.homeworklol.api.SummonerLeaguePosiApiClient;
import org.ajou.realcoding.lol.homeworklol.domain.InformationUser;
import org.ajou.realcoding.lol.homeworklol.domain.LeagueDTO;
import org.ajou.realcoding.lol.homeworklol.domain.SummonerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.ajou.realcoding.lol.homeworklol.repository.SummonerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class LolencryptedSummonerIdService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SummonerLeaguePosiApiClient summonerLeaguePosiApiClient;

    @Autowired
    SummonerIdApiClient summonerIdApiClient;

    @Autowired
    SummonerRepository summonerRepository;
    LinkedList<String> userNames = new LinkedList<>();


    @Scheduled(fixedDelay = 2000L)
    public void getInformationUserBySummonerName() {

       //userName에 다가 사용자 명 추가

        String username = userNames.pop();
        userNames.addLast(username);

        SummonerDTO summonerDTO = summonerIdApiClient.requestSummonerDTO(username);
        List<LeagueDTO> leagueDTOS = summonerLeaguePosiApiClient.requestLeagueDTO(summonerDTO.getId());
        InformationUser informationUser = new InformationUser();
        informationUser.setSummonerName(summonerDTO.getName());
        informationUser.setLeagueInfo(leagueDTOS);
        summonerRepository.insertSummoner(informationUser);
        log.info("lol user insert. {}",informationUser);
    }

    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public InformationUser getInformationUserToDB(@PathVariable String summonerName)

    {
        /*else
        {
            SummonerDTO summonerDTO= summonerIdApiClient.requestSummonerDTO(summonerName);
            List<LeagueDTO> leagueDTOS = summonerLeaguePosiApiClient.requestLeagueDTO(summonerDTO.getId());
            //InformationUser informationUser = new InformationUser();
            //informationUser.setSummonerName(summonerDTO.getName());
            //informationUser.setLeagueInfo(leagueDTOS);
            Update update = new Update();
            update.set("summonerName",summonerName);
            update.set("LeagueInfo",leagueDTOS);
            mongoTemplate.

        }*/
        InformationUser informationUser = summonerRepository.findInformationUser(summonerName);
        log.info("lol user find. {}",informationUser);
        return informationUser;

    }


}