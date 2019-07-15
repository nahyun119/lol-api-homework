package org.ajou.realcoding.lol.homeworklol.domain;


import lombok.Data;

import java.util.List;

@Data
public class InformationUser {

    private String summonerName;
    private List<LeagueDTO> leagueInfo;

}
