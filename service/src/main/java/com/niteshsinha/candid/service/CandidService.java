package com.niteshsinha.candid.service;

import com.niteshsinha.candid.model.Candid;
import com.niteshsinha.candid.repository.CandidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class CandidService {

    private final CandidRepository candidRepository;
    private final CandidClient candidClient;

    @Autowired
    public CandidService(CandidRepository candidRepository, CandidClient candidClient) {
        this.candidRepository = candidRepository;
        this.candidClient = candidClient;
    }

    @PostMapping("/candid/add")
    public Mono<Candid> add(@RequestBody Candid candid) {
        return candidRepository.save(candid);
    }

    @GetMapping("/candid/list")
    public Flux<Candid> get() {
        return candidRepository.findAll();
    }

//    @RequestMapping("/")
//    String index() {
//        return "index";
//    }
}
