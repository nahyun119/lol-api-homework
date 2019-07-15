package org.ajou.realcoding.lol.homeworklol.repository;


import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lol.homeworklol.domain.InformationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SummonerRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void insertSummoner(InformationUser informationUser) {
        if (findInformationUser(informationUser.getSummonerName()) == null) {
            mongoTemplate.insert(informationUser);
        }
        else {
            Query query = Query.query(Criteria.where("summonerName").is(informationUser.getSummonerName()));
            Update update = new Update();
            update.set("leagueInfo", informationUser.getLeagueInfo());
            log.info("update result : {}", mongoTemplate.updateFirst(query, update, InformationUser.class));
        }
    }

    public InformationUser findInformationUser(String summonerName) {
        Query query = Query.query(Criteria.where("summonerName").is(summonerName));


        return mongoTemplate.findOne(query, InformationUser.class);
    }
}
