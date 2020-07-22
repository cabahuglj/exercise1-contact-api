package com.codingcuriosity.example1.contact_api.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingcuriosity.example1.contact_api.entity.CommId;
import com.codingcuriosity.example1.contact_api.entity.Communication;
import com.codingcuriosity.example1.contact_api.service.CommunicationService;

@RestController
@RequestMapping("/communications")
public class CommunicationController {

  @Resource
  CommunicationService commService;

  @GetMapping(value = "/getAll/{contactId}")
  public List<Communication> getCommunications(
      @PathVariable(value = "contactId") String contactId) {
    return commService.findAll(contactId);
  }

  @PostMapping(value = "/add/{contactId}")
  public CommId createCommunication(@PathVariable(value = "contactId") String contactId,
      @RequestBody Communication dat) {
    return commService.insertCommunication(contactId, dat);
  }

  @PutMapping(value = "/update/{commId}")
  public void updateCommunication(@PathVariable(value = "commId") String commId,
      @RequestBody Communication dat) {
    commService.updateCommunication(commId, dat);
  }

  @DeleteMapping(value = "/delete/{commId}")
  public void deleteCommunication(@PathVariable(value = "commId") String commId) {
    commService.deleteCommunication(commId);
  }
}
